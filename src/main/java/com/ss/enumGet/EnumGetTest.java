package com.ss.enumGet;

/**
 * 枚举获取值测试
 * @author ChengXiao
 * @date 2021/7/26 11:50
 **/
public class EnumGetTest {
    public enum DownReportType {
        /**
         * 库存
         */
        MANAGE_INVENTORY(1, "库存"),

        /**
         * 月仓储费
         */
        STORAGE_FEE_CHARGES(2, "月仓储费");


        private final int code;
        private final String name;

        DownReportType(int code, String name) {
            this.code = code;
            this.name = name;
        }

        public int getCode() {
            return this.code;
        }

        public String getName() {
            return this.name;
        }

        public static String getName(Integer value) {
            DownReportType[] businessModeEnums = values();
            for (DownReportType businessModeEnum : businessModeEnums) {
                if (businessModeEnum.getCode() == value) {
                    return businessModeEnum.getName();
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        String name = DownReportType.getName(1);
        System.out.println(name);
    }
}
