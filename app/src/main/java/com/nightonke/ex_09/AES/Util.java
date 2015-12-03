package com.nightonke.ex_09.AES;

/**
 * Created by Weiping on 2015/11/28.
 */
public final class Util {

    public static final char[] HEX_DIGITS
            = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private Util() {
    }

    public static byte[] short2byte(short[] sa) {
        int length = sa.length;
        byte[] ba = new byte[length * 2];
        int i = 0;
        int j = 0;
        while (i < length) {
            short k = sa[i++];
            ba[j++] = (byte) (k >>> 8 & 255);
            ba[j++] = (byte) (k & 255);
        }
        return ba;
    }

    public static short[] byte2short(byte[] ba) {
        int length = ba.length;
        short[] sa = new short[length / 2];
        int i = 0;
        int j = 0;
        while (j < length / 2) {
            sa[j++] = (short) ((ba[i++] & 255) << 8 | ba[i++] & 255);
        }
        return sa;
    }

    public static byte[] int2byte(int[] ia) {
        int length = ia.length;
        byte[] ba = new byte[length * 4];
        int i = 0;
        int j = 0;
        while (i < length) {
            int k = ia[i++];
            ba[j++] = (byte) (k >>> 24 & 255);
            ba[j++] = (byte) (k >>> 16 & 255);
            ba[j++] = (byte) (k >>> 8 & 255);
            ba[j++] = (byte) (k & 255);
        }
        return ba;
    }

    public static int[] byte2int(byte[] ba) {
        int length = ba.length;
        int[] ia = new int[length / 4];
        int i = 0;
        int j = 0;
        while (j < length / 4) {
            ia[j++] = (ba[i++] & 255) << 24 | (ba[i++] & 255) << 16 | (ba[i++] & 255) << 8 | ba[i++] & 255;
        }
        return ia;
    }

    public static String toHEX(byte[] ba) {
        int length = ba.length;
        char[] buf = new char[length * 3];
        int i = 0;
        int j = 0;
        while (i < length) {
            byte k = ba[i++];
            buf[j++] = HEX_DIGITS[k >>> 4 & 15];
            buf[j++] = HEX_DIGITS[k & 15];
            buf[j++] = 32;
        }
        return new String(buf);
    }

    public static String toHEX(short[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 5];
        int i = 0;
        int j = 0;
        while (i < length) {
            short k = ia[i++];
            buf[j++] = HEX_DIGITS[k >>> 12 & 15];
            buf[j++] = HEX_DIGITS[k >>> 8 & 15];
            buf[j++] = HEX_DIGITS[k >>> 4 & 15];
            buf[j++] = HEX_DIGITS[k & 15];
            buf[j++] = 32;
        }
        return new String(buf);
    }

    public static String toHEX(int[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 10];
        int i = 0;
        int j = 0;
        while (i < length) {
            int k = ia[i++];
            buf[j++] = HEX_DIGITS[k >>> 28 & 15];
            buf[j++] = HEX_DIGITS[k >>> 24 & 15];
            buf[j++] = HEX_DIGITS[k >>> 20 & 15];
            buf[j++] = HEX_DIGITS[k >>> 16 & 15];
            buf[j++] = 32;
            buf[j++] = HEX_DIGITS[k >>> 12 & 15];
            buf[j++] = HEX_DIGITS[k >>> 8 & 15];
            buf[j++] = HEX_DIGITS[k >>> 4 & 15];
            buf[j++] = HEX_DIGITS[k & 15];
            buf[j++] = 32;
        }
        return new String(buf);
    }

    public static String toHEX1(byte b) {
        char[] buf = new char[2];
        int j = 0;
        buf[j++] = HEX_DIGITS[b >>> 4 & 15];
        buf[j++] = HEX_DIGITS[b & 15];
        return new String(buf);
    }

    public static String toHEX1(byte[] ba) {
        int length = ba.length;
        char[] buf = new char[length * 2];
        int i = 0;
        int j = 0;
        while (i < length) {
            byte k = ba[i++];
            buf[j++] = HEX_DIGITS[k >>> 4 & 15];
            buf[j++] = HEX_DIGITS[k & 15];
        }
        return new String(buf);
    }

    public static String toHEX1(short[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 4];
        int i = 0;
        int j = 0;
        while (i < length) {
            short k = ia[i++];
            buf[j++] = HEX_DIGITS[k >>> 12 & 15];
            buf[j++] = HEX_DIGITS[k >>> 8 & 15];
            buf[j++] = HEX_DIGITS[k >>> 4 & 15];
            buf[j++] = HEX_DIGITS[k & 15];
        }
        return new String(buf);
    }

    public static String toHEX1(int i) {
        char[] buf = new char[8];
        int j = 0;
        buf[j++] = HEX_DIGITS[i >>> 28 & 15];
        buf[j++] = HEX_DIGITS[i >>> 24 & 15];
        buf[j++] = HEX_DIGITS[i >>> 20 & 15];
        buf[j++] = HEX_DIGITS[i >>> 16 & 15];
        buf[j++] = HEX_DIGITS[i >>> 12 & 15];
        buf[j++] = HEX_DIGITS[i >>> 8 & 15];
        buf[j++] = HEX_DIGITS[i >>> 4 & 15];
        buf[j++] = HEX_DIGITS[i & 15];
        return new String(buf);
    }

    public static String toHEX1(int[] ia) {
        int length = ia.length;
        char[] buf = new char[length * 8];
        int i = 0;
        int j = 0;
        while (i < length) {
            int k = ia[i++];
            buf[j++] = HEX_DIGITS[k >>> 28 & 15];
            buf[j++] = HEX_DIGITS[k >>> 24 & 15];
            buf[j++] = HEX_DIGITS[k >>> 20 & 15];
            buf[j++] = HEX_DIGITS[k >>> 16 & 15];
            buf[j++] = HEX_DIGITS[k >>> 12 & 15];
            buf[j++] = HEX_DIGITS[k >>> 8 & 15];
            buf[j++] = HEX_DIGITS[k >>> 4 & 15];
            buf[j++] = HEX_DIGITS[k & 15];
        }
        return new String(buf);
    }

    public static byte[] hex2byte(String hex) {
        int len = hex.length();
        byte[] buf = new byte[(len + 1) / 2];
        int i = 0;
        int j = 0;
        if (len % 2 == 1) {
            buf[j++] = (byte) Util.hexDigit(hex.charAt(i++));
        }
        while (i < len) {
            buf[j++] = (byte) (Util.hexDigit(hex.charAt(i++)) << 4 | Util.hexDigit(hex.charAt(i++)));
        }
        return buf;
    }

    public static boolean isHex(String hex) {
        int len = hex.length();
        int i = 0;
        while (i < len) {
            char ch;
            if ((ch = hex.charAt(i++)) >= '0' && ch <= '9' || ch >= 'A' && ch <= 'F' || ch >= 'a' && ch <= 'f')
                continue;
            return false;
        }
        return true;
    }

    public static int hexDigit(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - 48;
        }
        if (ch >= 'A' && ch <= 'F') {
            return ch - 65 + 10;
        }
        if (ch >= 'a' && ch <= 'f') {
            return ch - 97 + 10;
        }
        return 0;
    }
}
