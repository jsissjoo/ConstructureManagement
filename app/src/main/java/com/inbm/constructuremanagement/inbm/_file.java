package com.inbm.constructuremanagement.inbm;

import android.webkit.MimeTypeMap;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class _file {
	public static String getMimeType(String url)
	{
		String type = null;
		String extension = MimeTypeMap.getFileExtensionFromUrl(url);
		if (extension != null) {
			MimeTypeMap mime = MimeTypeMap.getSingleton();
			type = mime.getMimeTypeFromExtension(extension);
		}
		return type;
	}

	public static String getExtension(String fileStr){
		return "." + fileStr.substring(fileStr.lastIndexOf(".")+1,fileStr.length());
	}

	public static String readFile(String path){
		String ret = "";
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				ret += sCurrentLine;
			}
		}catch(Exception e){
			_log.e(e.getMessage());
		}
		return ret;
	}

	public static void doUnzip(String inputZipFile, String destinationDirectory, String unique)
			throws IOException {
		int BUFFER = 2048;
		List zipFiles = new ArrayList();
		File sourceZipFile = new File(inputZipFile);
		File unzipDestinationDirectory = new File(destinationDirectory + File.separator + unique);
		unzipDestinationDirectory.mkdir();

		ZipFile zipFile;
		// Open Zip file for reading
		zipFile = new ZipFile(sourceZipFile, ZipFile.OPEN_READ);

		// Create an enumeration of the entries in the zip file
		Enumeration zipFileEntries = zipFile.entries();

		// Process each entry
		while (zipFileEntries.hasMoreElements()) {
			// grab a zip file entry
			ZipEntry entry = (ZipEntry) zipFileEntries.nextElement();

			String currentEntry = entry.getName();

			File destFile = new File(unzipDestinationDirectory, currentEntry);
//          destFile = new File(unzipDestinationDirectory, destFile.getName());

			if (currentEntry.endsWith(".zip")) {
				zipFiles.add(destFile.getAbsolutePath());
			}

			// grab file's parent directory structure
			File destinationParent = destFile.getParentFile();

			// create the parent directory structure if needed
			destinationParent.mkdirs();

			try {
				// extract file if not a directory
				if (!entry.isDirectory()) {
					BufferedInputStream is =
							new BufferedInputStream(zipFile.getInputStream(entry));
					int currentByte;
					// establish buffer for writing file
					byte data[] = new byte[BUFFER];

					// write the current file to disk
					FileOutputStream fos = new FileOutputStream(destFile);
					BufferedOutputStream dest =
							new BufferedOutputStream(fos, BUFFER);

					// read and write until last byte is encountered
					while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
						dest.write(data, 0, currentByte);
					}
					dest.flush();
					dest.close();
					is.close();
				}
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		zipFile.close();

		for (Iterator iter = zipFiles.iterator(); iter.hasNext();) {
			String zipName = (String)iter.next();
			doUnzip(
					zipName,
					destinationDirectory + File.separator + unique +
							File.separatorChar +
							zipName.substring(0,zipName.lastIndexOf(".zip")), unique
			);
		}

	}
}
