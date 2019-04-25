package com.seeds.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.seeds.web.config.ConfigurationManager;


public class FileUtils {

	private static Logger logger = LogManager.getLogger(FileUtils.class);
	
	private static final String UPLOAD_ROOT = ConfigurationManager.getInstance().getParameter("upload.root");
	private static final String UPLOAD_DIRECTORY = ConfigurationManager.getInstance().getParameter("upload.directory");
	
	public static void readDocument(HttpServletResponse response, String urlBase, Long idVideo) {
		
			File file = new File(urlBase);
			
			response.setHeader("Content-Disposition", "inline; filename="+ idVideo+".mp4;");
			response.setContentType("video/mp4");
			
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[1024];
				
				while (fis.read(buffer) > 0) {
					response.getOutputStream().write(buffer);
					response.flushBuffer();
				}
				fis.close();
			} catch (IOException e) {
				logger.warn(e.getMessage(), e);
			}
	}
	
	public static String loadDocument (Long idUsuario, Long idVideo,  FileItem fileItem) {
		// constructs the directory path to store upload file
        // this path is relative to application's directory
        String uploadPath = UPLOAD_ROOT + File.separator + UPLOAD_DIRECTORY + File.separator + idUsuario;
         
        // creates the directory if it does not exist

        
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            if(!uploadDir.mkdirs()) {
            	logger.warn("No se ha podido crear el directorio");
            }
        }
        
        if(logger.isDebugEnabled()) {
			logger.debug("UploadPath= {}",uploadPath);
		}
		
		String fileName = new File(idVideo.toString().concat(ConfigurationManager.getInstance().getParameter("files.extension"))).getName();
        String filePath = uploadPath + File.separator + fileName;
        File storeFile = new File(filePath);

        try {
			fileItem.write(storeFile);
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
		}
        logger.debug("Upload has been done successfully!");
        return filePath;
	}
	
}
