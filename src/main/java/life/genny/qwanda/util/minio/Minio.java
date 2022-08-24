package life.genny.qwanda.util.minio;


import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidArgumentException;
import io.minio.errors.InvalidBucketNameException;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import io.minio.errors.MinioException;
import io.minio.errors.NoResponseException;
import life.genny.qwanda.constant.MinIOConstant;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.UUID;

public class Minio {

    private static MinioClient minioClient;
    private static String REALM = Optional.ofNullable(System.getenv("REALM")).orElse("internmatch");

    static final org.jboss.logging.Logger log = Logger.getLogger(Minio.class);

    static {
        try {
            minioClient =
                    new MinioClient(MinIOConstant.MINIO_SERVER_URL,
                            MinIOConstant.MINIO_ACCESS_KEY,
                            MinIOConstant.MINIO_PRIVATE_KEY);
        } catch (InvalidEndpointException e) {
            log.error("Exception: " + e.getMessage());
        } catch (InvalidPortException e) {
            log.error("Exception: " + e.getMessage());
        }
    }

    public static String saveOnStore(FileUpload file) {
        boolean isUploaded = uploadFile(REALM.concat("/") + "public", file.uploadedFileName(), file.fileName());
        if (isUploaded) {
            return file.fileName();
        } else {
            return null;
        }
    }

    public static UUID saveOnStore(FileUpload file, UUID userUUID) {
        UUID randomUUID = UUID.randomUUID();
        boolean isUploaded = uploadFile(userUUID.toString(), file.uploadedFileName(), randomUUID.toString());
        if (isUploaded) {
            return randomUUID;
        } else {
            return null;
        }
    }

    public static byte[] fetchFromStoreUserDirectory(UUID fileUUID, UUID userUUID) {
        try {
            InputStream object = minioClient.getObject(MinIOConstant.BUCKET_NAME,
                    userUUID.toString() + "/media/" + fileUUID.toString());
            byte[] byteArray = IOUtils.toByteArray(object);
            return byteArray;
        } catch (InvalidKeyException | InvalidBucketNameException
                 | NoSuchAlgorithmException | InsufficientDataException
                 | NoResponseException | ErrorResponseException
                 | InternalException | InvalidArgumentException | IOException
                 | XmlPullParserException e) {
            log.error("Exception: " + e.getMessage());
            return new byte[]{};
        }
    }

    public static ObjectStat fetchStatFromStorePublicDirectory(UUID fileUUID) {
        try {
            ObjectStat object = minioClient.statObject(MinIOConstant.BUCKET_NAME,
                    REALM + "/" +
                            "public" + "/" +
                            "media" + "/" +
                            fileUUID.toString());
            return object;
        } catch (InvalidKeyException | InvalidBucketNameException
                 | NoSuchAlgorithmException | InsufficientDataException
                 | NoResponseException | ErrorResponseException
                 | InternalException | IOException
                 | XmlPullParserException e) {
            log.error("Exception: " + e.getMessage());
            return null;
        }
    }

    public static String fetchInfoFromStorePublicDirectory(UUID fileUUID) {
        try {
            InputStream object = minioClient.getObject(MinIOConstant.BUCKET_NAME,
                    REALM + "/" +
                            "public" + "/" +
                            "media" + "/" +
                            fileUUID.toString().concat("-info"));
            byte[] byteArray = IOUtils.toByteArray(object);
            return new String(byteArray);
        } catch (InvalidKeyException | InvalidBucketNameException
                 | NoSuchAlgorithmException | InsufficientDataException
                 | NoResponseException | ErrorResponseException
                 | InternalException | InvalidArgumentException | IOException
                 | XmlPullParserException e) {
            log.error("Exception: " + e.getMessage());
            return "";
        }
    }


    public static byte[] streamFromStorePublicDirectory(UUID fileUUID, Long start, Long end) {
        try {
            InputStream object = minioClient.getObject(MinIOConstant.BUCKET_NAME,
                    REALM + "/" +
                            "public" + "/" +
                            "media" + "/" +
                            fileUUID.toString(), start, end);
            byte[] byteArray = IOUtils.toByteArray(object);
            return byteArray;
        } catch (InvalidKeyException | InvalidBucketNameException
                 | NoSuchAlgorithmException | InsufficientDataException
                 | NoResponseException | ErrorResponseException
                 | InternalException | InvalidArgumentException | IOException
                 | XmlPullParserException e) {
            log.error("Exception: " + e.getMessage());
            return new byte[]{};
        }
    }

    public static byte[] fetchFromStorePublicDirectory(UUID fileUUID) {
        try {
            InputStream object = minioClient.getObject(MinIOConstant.BUCKET_NAME,
                    REALM + "/" +
                            "public" + "/" +
                            "media" + "/" +
                            fileUUID.toString());
            byte[] byteArray = IOUtils.toByteArray(object);
            return byteArray;
        } catch (InvalidKeyException | InvalidBucketNameException
                 | NoSuchAlgorithmException | InsufficientDataException
                 | NoResponseException | ErrorResponseException
                 | InternalException | InvalidArgumentException | IOException
                 | XmlPullParserException e) {
            log.error("Exception: " + e.getMessage());
            return new byte[]{};
        }
    }

    public static byte[] fetchFromStorePublicDirectory(String fileName) {
        try {
            InputStream object = minioClient.getObject(MinIOConstant.BUCKET_NAME,
                    REALM + "/" +
                            "public" + "/" +
                            "media" + "/" +
                            fileName);
            byte[] byteArray = IOUtils.toByteArray(object);
            return byteArray;
        } catch (InvalidKeyException | InvalidBucketNameException
                 | NoSuchAlgorithmException | InsufficientDataException
                 | NoResponseException | ErrorResponseException
                 | InternalException | InvalidArgumentException | IOException
                 | XmlPullParserException e) {
            log.error("Exception: " + e.getMessage());
            return new byte[]{};
        }
    }

    public static void deleteFromStorePublicDirectory(UUID fileUUID) {
        try {
            minioClient.removeObject(MinIOConstant.BUCKET_NAME,
                    REALM + "/" +
                            "public" + "/" +
                            "media" + "/" +
                            fileUUID.toString());
        } catch (InvalidKeyException | InvalidBucketNameException
                 | NoSuchAlgorithmException | InsufficientDataException
                 | NoResponseException | ErrorResponseException
                 | InternalException | InvalidArgumentException | IOException
                 | XmlPullParserException e) {
            log.error("Exception: " + e.getMessage());
        }
    }

    public static boolean uploadFile(String sub, String inpt, String uuid) {
        boolean isSuccess = false;

        String path = sub + "/" + "media" + "/" + uuid;
        try {
            boolean isExist = minioClient.bucketExists(MinIOConstant.BUCKET_NAME);
            if (isExist) {
                log.info("Bucket " + MinIOConstant.BUCKET_NAME + "already exists.");
            } else {
                log.info("Start creat Bucket:" + MinIOConstant.BUCKET_NAME);
                minioClient.makeBucket(MinIOConstant.BUCKET_NAME);
                log.info("Finish create Bucket:" + MinIOConstant.BUCKET_NAME);
            }

            minioClient.putObject(MinIOConstant.BUCKET_NAME, path, inpt);
            isSuccess = true;
            log.info("Success, File" + inpt + " uploaded to bucket with path:" + path);
        } catch (MinioException | InvalidKeyException
                 | NoSuchAlgorithmException | IOException
                 | XmlPullParserException e) {
            log.error("Error occurred when upload file to bucket: " + e.getMessage());
        }
        return isSuccess;
    }
}
