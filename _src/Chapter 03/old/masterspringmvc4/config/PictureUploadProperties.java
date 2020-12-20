package masterspringmvc4.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@ConfigurationProperties(prefix = "upload.pictures")
public class PictureUploadProperties {
    private Path uploadPath;
    private Path anonymousPicture;

    public Path getAnonymousPicture() {
        return anonymousPicture;
    }

    public void setAnonymousPicture(String anonymousPicture) {
        this.anonymousPicture = getResourcePath(anonymousPicture);
    }

    public Path getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = Paths.get(uploadPath);
        if (!Files.isDirectory(this.uploadPath)) {
            throw new IllegalStateException("Directory " + uploadPath + " does not exist");
        }
    }

    private Path getResourcePath(String resourcePath) {
        try {
            URL url = PictureUploadProperties.class.getResource(resourcePath);
            return Paths.get(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Could not find resource " + resourcePath, e);
        }
    }
}
