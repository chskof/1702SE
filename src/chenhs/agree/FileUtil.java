package chenhs.agree;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileUtil {
	

	public static boolean isServerFileExists(String path) throws IOException {
		File file = new File(path);

		return (file.exists()) && (file.isFile());
	}

	public static String writeFile(String path, byte[] content, boolean append) throws IOException {
		if ((path == null) || (path.length() == 0)) {
			path = File.createTempFile("writeServerFile", ".file").getAbsolutePath();
		} else {
			path = new File(path).getAbsolutePath();
		}
		OutputStream os = null;
		try {
			os = new BufferedOutputStream(new FileOutputStream(path, append));
			os.write(content);
			os.flush();
		} finally {
			if (os != null) {
				os.close();
			}
		}
		return path;
	}

	public static boolean isFileExists(String path) throws IOException {
		
		return true;
	}
	
	public static void deleteServerFile(String path) throws IOException {
		File file = new File(path);
		if ((file.exists()) && (file.isFile())) {
			file.delete();
			return;
		}
		throw new FileNotFoundException("无法找到文件:" + path);
	}
}
