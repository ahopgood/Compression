package com.alexander.compression;

import java.io.InputStream;
import java.io.OutputStream;

public interface Compressor {

	public void readCompressedFile(InputStream stream);
	
	public boolean writeCompressedFile(OutputStream output);
}
