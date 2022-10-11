package life.genny.qwanda.util.minio;


import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import io.minio.UploadObjectArgs;
import life.genny.qwanda.constant.MinIOConstant;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;

import java.io.File;
import java.util.Optional;
import java.util.UUID;

public class Minio {

    private static MinioClient minioClient;
    private static String REALM = Optional.ofNullable(System.getenv("REALM")).orElse("internmatch");

    private static final String PUBLIC_MEDIA_PATH = "/public/media/";
    private static final String PUBLIC_PATH = "/public";
    private static final String MEDIA_PATH = "/media/";
    private static final String INFO_FILE_SUFFIX = "-info";

    static final org.jboss.logging.Logger log = Logger.getLogger(Minio.class);

    static {
        try {
            minioClient = MinioClient.builder().endpoint(MinIOConstant.MINIO_SERVER_URL).credentials(MinIOConstant.MINIO_ACCESS_KEY, MinIOConstant.MINIO_PRIVATE_KEY).build();
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
        }
    }

    public static String saveOnStore(FileUpload file) {
        boolean isUploaded = uploadFile(REALM + PUBLIC_PATH, file.uploadedFileName(), file.fileName());
        if (isUploaded) {
            return file.fileName();
        } else {
            return null;
        }
    }


    public static String saveOnStore(String fileName, File file) {
        Boolean isFileUploaded = uploadFile(REALM + PUBLIC_PATH, file.getPath(), fileName);
        if (isFileUploaded) {
            return fileName;
        } else {
            return null;
        }
    }

    public static UUID saveOnStore(FileUpload file, UUID userUUID) {
        UUID randomUUID = UUID.randomUUID();
        Boolean isFileUploaded = uploadFile(userUUID.toString(), file.uploadedFileName(), randomUUID.toString());
        if (isFileUploaded) {
            return randomUUID;
        } else {
            return null;
        }
    }

    public static byte[] fetchFromStoreUserDirectory(UUID fileUUID, UUID userUUID) {
        try {
            String fullPath = REALM + "/" + userUUID.toString() + MEDIA_PATH + fileUUID.toString() + INFO_FILE_SUFFIX;
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(MinIOConstant.BUCKET_NAME).object(fullPath).build();
            GetObjectResponse getObjectResponse = minioClient.getObject(getObjectArgs);
            byte[] byteArray = IOUtils.toByteArray(getObjectResponse);
            return byteArray;
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
            return new byte[]{};
        }
    }

    public static StatObjectResponse fetchStatFromStorePublicDirectory(UUID fileUUID) {
        return fetchStatFromStorePublicDirectory(fileUUID.toString());
    }

    public static StatObjectResponse fetchStatFromStorePublicDirectory(String fileUUID) {
        try {
            String fullPath = REALM + PUBLIC_MEDIA_PATH + fileUUID;
            StatObjectArgs statObjectArgs = StatObjectArgs.builder().bucket(MinIOConstant.BUCKET_NAME).object(fullPath).build();
            StatObjectResponse statObjectResponse = minioClient.statObject(statObjectArgs);
            return statObjectResponse;
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
            return null;
        }
    }

    public static String fetchInfoFromStorePublicDirectory(UUID fileUUID) {
        return fetchInfoFromStorePublicDirectory(fileUUID.toString());
    }

    public static String fetchInfoFromStorePublicDirectory(String fileUUID) {
        try {
            String fullPath = REALM + PUBLIC_MEDIA_PATH + fileUUID + INFO_FILE_SUFFIX;
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(MinIOConstant.BUCKET_NAME).object(fullPath).build();
            GetObjectResponse getObjectResponse = minioClient.getObject(getObjectArgs);
            byte[] byteArray = IOUtils.toByteArray(getObjectResponse);
            return new String(byteArray);
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
            return "";
        }
    }

    public static byte[] streamFromStorePublicDirectory(UUID fileUUID, Long start, Long end) {
        return streamFromStorePublicDirectory(fileUUID.toString(), start, end);
    }

    public static byte[] streamFromStorePublicDirectory(String fileUUID, Long start, Long end) {
        try {
            String fullPath = REALM + PUBLIC_MEDIA_PATH + fileUUID.toString();
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(MinIOConstant.BUCKET_NAME).object(fullPath).offset(start).length(end).build();
            GetObjectResponse getObjectResponse = minioClient.getObject(getObjectArgs);
            byte[] byteArray = IOUtils.toByteArray(getObjectResponse);
            return byteArray;
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
            return new byte[]{};
        }
    }

    public static byte[] fetchFromStorePublicDirectory(UUID fileUUID) {
        try {
            String fullPath = REALM + PUBLIC_MEDIA_PATH + fileUUID.toString();
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(MinIOConstant.BUCKET_NAME).object(fullPath).build();
            GetObjectResponse getObjectResponse = minioClient.getObject(getObjectArgs);
            byte[] byteArray = IOUtils.toByteArray(getObjectResponse);
            return byteArray;
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
            return new byte[]{};
        }
    }

    public static byte[] fetchFromStorePublicDirectory(String fileName) {
        try {
            String fullPath = REALM + PUBLIC_MEDIA_PATH + fileName;
            GetObjectArgs getObjectArgs = GetObjectArgs.builder().bucket(MinIOConstant.BUCKET_NAME).object(fullPath).build();
            GetObjectResponse getObjectResponse = minioClient.getObject(getObjectArgs);
            byte[] byteArray = IOUtils.toByteArray(getObjectResponse);
            return byteArray;
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
            return new byte[]{};
        }
    }

    public static void deleteFromStorePublicDirectory(UUID fileUUID) {
        try {
            String fullPath = REALM + PUBLIC_MEDIA_PATH + fileUUID.toString();
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs.builder().bucket(MinIOConstant.BUCKET_NAME).object(fullPath).build();
            minioClient.removeObject(removeObjectArgs);
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
        }
    }

    public static boolean uploadFile(String sub, String inpt, String uuid) {
        boolean isSuccess = false;

        String path = sub + MEDIA_PATH + uuid;
        try {
            BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder().bucket(MinIOConstant.BUCKET_NAME).build();

            boolean isExist = minioClient.bucketExists(bucketExistsArgs);
            if (isExist) {
                log.debug("Bucket " + MinIOConstant.BUCKET_NAME + "already exists.");
            } else {
                log.debug("Start creat Bucket:" + MinIOConstant.BUCKET_NAME);
                MakeBucketArgs makeBucketArgs = MakeBucketArgs.builder().bucket(MinIOConstant.BUCKET_NAME).build();
                minioClient.makeBucket(makeBucketArgs);
                log.debug("Finish create Bucket:" + MinIOConstant.BUCKET_NAME);
            }

            UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder().bucket(MinIOConstant.BUCKET_NAME).object(path).filename(inpt).build();

            minioClient.uploadObject(uploadObjectArgs);

            isSuccess = true;
            log.debug("Success, File" + inpt + " uploaded to bucket with path:" + path);
        } catch (Exception ex) {
            log.error("Exception: " + ex.getMessage());
        }
        return isSuccess;
    }
}
