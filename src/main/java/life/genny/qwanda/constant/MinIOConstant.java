package life.genny.qwanda.constant;

import java.util.Optional;

public class MinIOConstant {

    public final static String MINIO_SERVER_URL;
    public final static String MINIO_ACCESS_KEY;
    public final static String MINIO_PRIVATE_KEY;
    public final static String BUCKET_NAME;
    // Fallback message if MinIO throws an exception fetching the object from MinIO
    public final static String ERROR_FALLBACK_MSG = "Error Occurred";

    public final static String TEMP_FILE_PATH = "file-uploads/";

    static {
        /* Values from Environment */
        Optional<String> minioServerURL = Optional.ofNullable(System.getenv(SystemEnvironmentConstant.MINIO_SERVER_URL));
        Optional<String> minioAccessKey = Optional.ofNullable(System.getenv(SystemEnvironmentConstant.MINIO_ACCESS_KEY));
        Optional<String> minioPrivateKey = Optional.ofNullable(System.getenv(SystemEnvironmentConstant.MINIO_PRIVATE_KEY));
        Optional<String> bucketName = Optional.ofNullable(System.getenv(SystemEnvironmentConstant.BUCKET_NAME));

        /* Or Else Default Values */
        MINIO_SERVER_URL = minioServerURL.orElse("http://localhost:9000");
        MINIO_ACCESS_KEY = minioAccessKey.orElse("minioadmin");
        MINIO_PRIVATE_KEY = minioPrivateKey.orElse("minioadmin");
        BUCKET_NAME = bucketName.orElse("internmatch");
    }

}
