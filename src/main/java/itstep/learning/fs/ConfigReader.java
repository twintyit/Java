package itstep.learning.fs;

import sun.security.x509.DNSName;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {

    public DbModel readConfigAndCreateConnectionString() {
        try (InputStream rs =
                     this.getClass().getClassLoader().getResourceAsStream("db.ini")
        ) {
            String content = readStream(rs);
            Map<String, String> ini = new HashMap<>();

            String[] lines = content.split("\n");
            for (String line : lines) {
                String[] parts = line.split("=");
                ini.put(parts[0].trim(), parts[1].trim());
            }
            String Url =  String.format("jdbc:%s://%s:%s/%s?useUnicode=%s&characterEncoding=%s",
                                    ini.get("dbms"),
                                    ini.get("host"),
                                    ini.get("port"),
                                    ini.get("schema"),
                                    ini.get("useUnicode"),
                                    ini.get("characterEncoding"));
            String User = ini.get("user");
            String Pass = ini.get("password");

            DbModel dbModel = new DbModel();
            dbModel.setURL(Url);
            dbModel.setUSER(User);
            dbModel.setPASSWORD(Pass);

            return dbModel;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private String readStream(InputStream in) throws IOException {
        byte[] buffer = new byte[4096];
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             BufferedInputStream bis = new BufferedInputStream(in)) {
            int len;
            while ((len = bis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            return baos.toString(StandardCharsets.UTF_8.name());
        }
    }
}
