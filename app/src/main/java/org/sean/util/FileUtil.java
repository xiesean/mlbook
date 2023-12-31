package org.sean.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.arch.core.util.Function;

import com.google.android.gms.common.util.Hex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by Administrator on 2015/8/21.
 */
public class FileUtil {

    static final int WHAT_SUCCESS = 11;
    static final int WHAT_FAILED = 12;

    public static void encode(String src, String des) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            fi = new FileInputStream(src);
            fo = new FileOutputStream(des);

            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            byte tmp = -1;
            while ((len = fi.read(buffer)) != -1) {
                // 交换位置
                for (int i = 0; i < len / 2; i++) {
                    tmp = buffer[(len - 1) - i];
                    buffer[(len - 1) - i] = buffer[i];
                    buffer[i] = tmp;
                }
                fo.write(buffer, 0, len);
            }
            fo.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
                if (fo != null) {
                    fo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void decode(InputStream in, String des) {
        FileOutputStream fo = null;
        try {
            File outFile = new File(des);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            fo = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            byte tmp = -1;
            while ((len = in.read(buffer)) != -1) {
                // 交换位置
                for (int i = 0; i < len / 2; i++) {
                    tmp = buffer[(len - 1) - i];
                    buffer[(len - 1) - i] = buffer[i];
                    buffer[i] = tmp;
                }
                fo.write(buffer, 0, len);
            }
            fo.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (Exception e) {
                }
            }
        }
    }


    public static void encodeByte(String src, String des) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {

            File outFile = new File(des);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            fi = new FileInputStream(src);
            fo = new FileOutputStream(des);

            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            byte tmp = -1;
            while ((len = fi.read(buffer)) != -1) {
                // 交换位置
                for (int i = 0; i < len / 2; i++) {
                    tmp = buffer[(len - 1) - i];
                    buffer[(len - 1) - i] = buffer[i];
                    buffer[i] = tmp;
                }
                fo.write(buffer, 0, len);
            }
            fo.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
                if (fo != null) {
                    fo.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public static void decodeFromAsses(Context context, String fileName, String des) {
        InputStream in = null;
        FileOutputStream fo = null;
        try {
            in = context.getAssets().open(fileName);
            File outFile = new File(des);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            fo = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            byte tmp = -1;
            while ((len = in.read(buffer)) != -1) {
                // 交换位置
                for (int i = 0; i < len / 2; i++) {
                    tmp = buffer[(len - 1) - i];
                    buffer[(len - 1) - i] = buffer[i];
                    buffer[i] = tmp;
                }
                fo.write(buffer, 0, len);
            }
            fo.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static void copyFromAsses(Context context, String fileName, String des) {
        InputStream in = null;
        FileOutputStream fo = null;
        try {
            in = context.getAssets().open(fileName);
            File outFile = new File(des);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            fo = new FileOutputStream(outFile);

            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                fo.write(buffer, 0, len);
            }
            fo.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (Exception e) {
                }
            }
        }
    }

    public static String readFromAsses(Context context, String fileName) {
        InputStream in = null;
        ByteArrayOutputStream fo = new ByteArrayOutputStream();
        try {
            in = context.getAssets().open(fileName);
            byte[] buffer = new byte[1024 * 8];
            int len = -1;
            while ((len = in.read(buffer)) != -1) {
                fo.write(buffer, 0, len);
            }
            fo.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
            if (fo != null) {
                try {
                    fo.close();
                } catch (Exception e) {
                }
            }
        }
        return fo.toString();
    }

    /**
     * 使用文件通道的方式复制文件
     *
     * @param src 源文件
     * @param des 复制到的新文件
     */

    public static void fileChannelCopy(String src, String des) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;

        try {
            fi = new FileInputStream(src);
            fo = new FileOutputStream(des);
            in = fi.getChannel();//得到对应的文件通道
            out = fo.getChannel();//得到对应的文件通道
            in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fi != null) {
                    fi.close();
                }
                if (in != null) {
                    in.close();
                }
                if (fo != null) {
                    fo.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void fileCopyByLine(InputStream src, String des, Function<String, String> fun) {
        BufferedReader br = null;
        BufferedWriter bw = null;

        try {
            br = new BufferedReader(new InputStreamReader(src));
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(des)));
            String line = null;
            while (null != (line = br.readLine())) {
                bw.write(fun.apply(line));
                bw.write("\n");
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //解压指定zip文件
    public static boolean unZip(File zipfile, Handler handler) {//unZipfileName需要解压的zip文件名
        String successFile = zipfile.getAbsolutePath() + ".sul";
        File success = new File(successFile);
        if (success.exists()) {
            return true;
        }
        FileOutputStream fileOut = null;
        File file;
        InputStream inputStream = null;
        ZipFile zipFile = null;

        try {
            String filePath = zipfile.getAbsolutePath();
            filePath = filePath.substring(0, filePath.lastIndexOf('.'));
            zipFile = new ZipFile(zipfile);
            for (Enumeration entries = zipFile.entries(); entries.hasMoreElements(); ) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                file = new File(filePath + File.separator + entry.getName());

                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    //如果指定文件的目录不存在,则创建之.
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }

                    inputStream = zipFile.getInputStream(entry);

                    fileOut = new FileOutputStream(file);
                    int len = -1;
                    byte[] buf = new byte[1024 * 8];
                    while ((len = inputStream.read(buf)) > 0) {
                        fileOut.write(buf, 0, len);
                    }
                    inputStream.close();
                    fileOut.close();
                    zipFile.close();
                }
            }
            if (handler != null) {
                Message msg = handler.obtainMessage(WHAT_SUCCESS);
                msg.obj = filePath;
                handler.sendMessage(msg);
            }
            try {
                success.createNewFile();
            } catch (Exception e) {
                FileOutputStream fos = null;
                try {
                    fos = new FileOutputStream(success);
                    fos.write("success".getBytes());
                    fos.flush();
                } catch (Exception ex) {
                } finally {
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (Exception e1) {
                    }
                }
            }
            return true;
        } catch (Exception ioe) {
            ioe.printStackTrace();
            if (handler != null) {
                handler.sendEmptyMessage(WHAT_FAILED);
            }
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                }
            }
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (Exception e) {
                }
            }
            if (fileOut != null) {
                try {
                    fileOut.close();
                } catch (Exception e) {
                }
            }
        }
        return false;
    }

    public static String getMD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            fileInputStream = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length;
            while ((length = fileInputStream.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return Hex.bytesToStringLowercase(MD5.digest());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
