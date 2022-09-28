import com.ip2location.IPTools;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Ip2locationTest {
    private static int count = 0;

    @Test
    public void ipv4AndIpv6() throws IOException {
        // 文件来源：https://lite.ip2location.com/database/ip-country?lang=zh_CN
        String filename = "D:\\Temp\\IP2LOCATION-LITE-DB1.CSV\\IP2LOCATION-LITE-DB1.CSV";
        Mode mode = Mode.ONLY_IPV4;

        File file = new File(new File(filename).getParentFile().getAbsolutePath() + "\\proxifier-rule.txt");
        boolean createResult = file.createNewFile();

        List<IpLocation> list = readCsv(filename);
        IPTools ipTools = new IPTools();
        StringBuilder result = new StringBuilder();

        int startIndex = 0;
        int endIndex = 0;
        while (startIndex < list.size()) {
            IpLocation start = list.get(startIndex);
            if ("CN".equals(start.getCountryCode()) || "-".equals(start.getCountryCode())) {
                startIndex++;
                continue;
            }
            for (endIndex = startIndex + 1; endIndex < list.size(); endIndex++) {
                IpLocation end = list.get(endIndex);
                if ("CN".equals(end.getCountryCode()) || "-".equals(end.getCountryCode())) {
                    break;
                }
            }
            if (mode == Mode.ONLY_IPV4) {
                String from = ipTools.DecimalToIPv4(new BigInteger(start.getIpStart()));
                String to = ipTools.DecimalToIPv4(new BigInteger(list.get(endIndex - 1).getIpEnd()));
                result.append(from).append("-").append(to).append(";");
            } else {
                String from = ipTools.DecimalToIPv6(new BigInteger(start.getIpStart()));
                String to = ipTools.DecimalToIPv6(new BigInteger(list.get(endIndex - 1).getIpEnd()));
                result.append(from).append("-").append(to).append(";");
            }
            if (result.length() > 32000) {
                write(file, result);
                result = new StringBuilder();
            }
            startIndex = endIndex;
        }
        if (result.length() > 0) {
            write(file, result);
        }
    }

    private void write(File file, StringBuilder result) throws IOException {
        result.insert(0, "\t\t<Rule enabled=\"true\">\n" +
                "\t\t\t<Action type=\"Proxy\">100</Action>\n" +
                "\t\t\t<Targets>").append("</Targets>\n" +
                "\t\t\t<Name>IP-PROXY-").append(++count).append("</Name>\n" +
                "\t\t</Rule>\n");
        try (FileOutputStream outputStream = new FileOutputStream(file, true)) {
            outputStream.write(result.toString().getBytes(StandardCharsets.UTF_8));
        }
    }

    private static List<IpLocation> readCsv(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        List<IpLocation> list = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.replace("\"", "");
            list.add(new IpLocation(line.split(",")));
        }
        return list;
    }

    private enum Mode {
        ONLY_IPV4, IPV4_AND_IPV6
    }

    private static class IpLocation {
        private String ipStart;
        private String ipEnd;
        private String countryCode;
        private String countryName;

        public IpLocation(String[] splits) {
            this.ipStart = splits[0];
            this.ipEnd = splits[1];
            this.countryCode = splits[2];
            this.countryName = splits[3];
        }

        public String getIpStart() {
            return ipStart;
        }

        public void setIpStart(String ipStart) {
            this.ipStart = ipStart;
        }

        public String getIpEnd() {
            return ipEnd;
        }

        public void setIpEnd(String ipEnd) {
            this.ipEnd = ipEnd;
        }

        public String getCountryCode() {
            return countryCode;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
    }

}
