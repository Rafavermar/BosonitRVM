package com.block11uploaddownloadfiles.block11uploaddownloadfiles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class Block11UploadDownloadFilesApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Block11UploadDownloadFilesApplication.class);
		if (args.length > 0) {
			app.setDefaultProperties(Collections.singletonMap("file.storage.path", args[0]));
		}
		app.run(args);
	}

}
