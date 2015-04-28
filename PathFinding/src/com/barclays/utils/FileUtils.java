package com.barclays.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class FileUtils {
	Scanner	sc;
	RandomAccessFile	seek;

	public FileUtils(String source) throws FileNotFoundException {
		seek	=	new RandomAccessFile(source, "r");
	}

	public FileUtils(InputStream source) throws FileNotFoundException {
		sc	=	new Scanner(source);
	}

	public String readLine() throws IOException
	{
		if(seek!=null)
			return	seek.readLine();
		return sc.nextLine();
	}

}
