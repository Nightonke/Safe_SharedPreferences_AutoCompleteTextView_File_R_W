package com.nightonke.ex_09.AES;

import java.util.Arrays;

/**
 * Created by Weiping on 2015/11/28.
 */

public class AES {
    public int traceLevel = 0;
    public String traceInfo = "";
    public static final int ROUNDS = 14;
    public static final int BLOCK_SIZE = 16;
    public static final int KEY_LENGTH = 32;
    int numRounds;
    byte[][] Ke;
    byte[][] Kd;
    static final byte[] S;
    static final byte[] Si;
    static final byte[] rcon;
    public static final int COL_SIZE = 4;
    public static final int NUM_COLS = 4;
    public static final int ROOT = 283;
    static final int[] row_shift;
    static final int[] alog;
    static final int[] log;

    static {
        byte[] arrby = new byte[256];
        arrby[0] = 99;
        arrby[1] = 124;
        arrby[2] = 119;
        arrby[3] = 123;
        arrby[4] = -14;
        arrby[5] = 107;
        arrby[6] = 111;
        arrby[7] = -59;
        arrby[8] = 48;
        arrby[9] = 1;
        arrby[10] = 103;
        arrby[11] = 43;
        arrby[12] = -2;
        arrby[13] = -41;
        arrby[14] = -85;
        arrby[15] = 118;
        arrby[16] = -54;
        arrby[17] = -126;
        arrby[18] = -55;
        arrby[19] = 125;
        arrby[20] = -6;
        arrby[21] = 89;
        arrby[22] = 71;
        arrby[23] = -16;
        arrby[24] = -83;
        arrby[25] = -44;
        arrby[26] = -94;
        arrby[27] = -81;
        arrby[28] = -100;
        arrby[29] = -92;
        arrby[30] = 114;
        arrby[31] = -64;
        arrby[32] = -73;
        arrby[33] = -3;
        arrby[34] = -109;
        arrby[35] = 38;
        arrby[36] = 54;
        arrby[37] = 63;
        arrby[38] = -9;
        arrby[39] = -52;
        arrby[40] = 52;
        arrby[41] = -91;
        arrby[42] = -27;
        arrby[43] = -15;
        arrby[44] = 113;
        arrby[45] = -40;
        arrby[46] = 49;
        arrby[47] = 21;
        arrby[48] = 4;
        arrby[49] = -57;
        arrby[50] = 35;
        arrby[51] = -61;
        arrby[52] = 24;
        arrby[53] = -106;
        arrby[54] = 5;
        arrby[55] = -102;
        arrby[56] = 7;
        arrby[57] = 18;
        arrby[58] = -128;
        arrby[59] = -30;
        arrby[60] = -21;
        arrby[61] = 39;
        arrby[62] = -78;
        arrby[63] = 117;
        arrby[64] = 9;
        arrby[65] = -125;
        arrby[66] = 44;
        arrby[67] = 26;
        arrby[68] = 27;
        arrby[69] = 110;
        arrby[70] = 90;
        arrby[71] = -96;
        arrby[72] = 82;
        arrby[73] = 59;
        arrby[74] = -42;
        arrby[75] = -77;
        arrby[76] = 41;
        arrby[77] = -29;
        arrby[78] = 47;
        arrby[79] = -124;
        arrby[80] = 83;
        arrby[81] = -47;
        arrby[83] = -19;
        arrby[84] = 32;
        arrby[85] = -4;
        arrby[86] = -79;
        arrby[87] = 91;
        arrby[88] = 106;
        arrby[89] = -53;
        arrby[90] = -66;
        arrby[91] = 57;
        arrby[92] = 74;
        arrby[93] = 76;
        arrby[94] = 88;
        arrby[95] = -49;
        arrby[96] = -48;
        arrby[97] = -17;
        arrby[98] = -86;
        arrby[99] = -5;
        arrby[100] = 67;
        arrby[101] = 77;
        arrby[102] = 51;
        arrby[103] = -123;
        arrby[104] = 69;
        arrby[105] = -7;
        arrby[106] = 2;
        arrby[107] = 127;
        arrby[108] = 80;
        arrby[109] = 60;
        arrby[110] = -97;
        arrby[111] = -88;
        arrby[112] = 81;
        arrby[113] = -93;
        arrby[114] = 64;
        arrby[115] = -113;
        arrby[116] = -110;
        arrby[117] = -99;
        arrby[118] = 56;
        arrby[119] = -11;
        arrby[120] = -68;
        arrby[121] = -74;
        arrby[122] = -38;
        arrby[123] = 33;
        arrby[124] = 16;
        arrby[125] = -1;
        arrby[126] = -13;
        arrby[127] = -46;
        arrby[128] = -51;
        arrby[129] = 12;
        arrby[130] = 19;
        arrby[131] = -20;
        arrby[132] = 95;
        arrby[133] = -105;
        arrby[134] = 68;
        arrby[135] = 23;
        arrby[136] = -60;
        arrby[137] = -89;
        arrby[138] = 126;
        arrby[139] = 61;
        arrby[140] = 100;
        arrby[141] = 93;
        arrby[142] = 25;
        arrby[143] = 115;
        arrby[144] = 96;
        arrby[145] = -127;
        arrby[146] = 79;
        arrby[147] = -36;
        arrby[148] = 34;
        arrby[149] = 42;
        arrby[150] = -112;
        arrby[151] = -120;
        arrby[152] = 70;
        arrby[153] = -18;
        arrby[154] = -72;
        arrby[155] = 20;
        arrby[156] = -34;
        arrby[157] = 94;
        arrby[158] = 11;
        arrby[159] = -37;
        arrby[160] = -32;
        arrby[161] = 50;
        arrby[162] = 58;
        arrby[163] = 10;
        arrby[164] = 73;
        arrby[165] = 6;
        arrby[166] = 36;
        arrby[167] = 92;
        arrby[168] = -62;
        arrby[169] = -45;
        arrby[170] = -84;
        arrby[171] = 98;
        arrby[172] = -111;
        arrby[173] = -107;
        arrby[174] = -28;
        arrby[175] = 121;
        arrby[176] = -25;
        arrby[177] = -56;
        arrby[178] = 55;
        arrby[179] = 109;
        arrby[180] = -115;
        arrby[181] = -43;
        arrby[182] = 78;
        arrby[183] = -87;
        arrby[184] = 108;
        arrby[185] = 86;
        arrby[186] = -12;
        arrby[187] = -22;
        arrby[188] = 101;
        arrby[189] = 122;
        arrby[190] = -82;
        arrby[191] = 8;
        arrby[192] = -70;
        arrby[193] = 120;
        arrby[194] = 37;
        arrby[195] = 46;
        arrby[196] = 28;
        arrby[197] = -90;
        arrby[198] = -76;
        arrby[199] = -58;
        arrby[200] = -24;
        arrby[201] = -35;
        arrby[202] = 116;
        arrby[203] = 31;
        arrby[204] = 75;
        arrby[205] = -67;
        arrby[206] = -117;
        arrby[207] = -118;
        arrby[208] = 112;
        arrby[209] = 62;
        arrby[210] = -75;
        arrby[211] = 102;
        arrby[212] = 72;
        arrby[213] = 3;
        arrby[214] = -10;
        arrby[215] = 14;
        arrby[216] = 97;
        arrby[217] = 53;
        arrby[218] = 87;
        arrby[219] = -71;
        arrby[220] = -122;
        arrby[221] = -63;
        arrby[222] = 29;
        arrby[223] = -98;
        arrby[224] = -31;
        arrby[225] = -8;
        arrby[226] = -104;
        arrby[227] = 17;
        arrby[228] = 105;
        arrby[229] = -39;
        arrby[230] = -114;
        arrby[231] = -108;
        arrby[232] = -101;
        arrby[233] = 30;
        arrby[234] = -121;
        arrby[235] = -23;
        arrby[236] = -50;
        arrby[237] = 85;
        arrby[238] = 40;
        arrby[239] = -33;
        arrby[240] = -116;
        arrby[241] = -95;
        arrby[242] = -119;
        arrby[243] = 13;
        arrby[244] = -65;
        arrby[245] = -26;
        arrby[246] = 66;
        arrby[247] = 104;
        arrby[248] = 65;
        arrby[249] = -103;
        arrby[250] = 45;
        arrby[251] = 15;
        arrby[252] = -80;
        arrby[253] = 84;
        arrby[254] = -69;
        arrby[255] = 22;
        S = arrby;
        byte[] arrby2 = new byte[256];
        arrby2[0] = 82;
        arrby2[1] = 9;
        arrby2[2] = 106;
        arrby2[3] = -43;
        arrby2[4] = 48;
        arrby2[5] = 54;
        arrby2[6] = -91;
        arrby2[7] = 56;
        arrby2[8] = -65;
        arrby2[9] = 64;
        arrby2[10] = -93;
        arrby2[11] = -98;
        arrby2[12] = -127;
        arrby2[13] = -13;
        arrby2[14] = -41;
        arrby2[15] = -5;
        arrby2[16] = 124;
        arrby2[17] = -29;
        arrby2[18] = 57;
        arrby2[19] = -126;
        arrby2[20] = -101;
        arrby2[21] = 47;
        arrby2[22] = -1;
        arrby2[23] = -121;
        arrby2[24] = 52;
        arrby2[25] = -114;
        arrby2[26] = 67;
        arrby2[27] = 68;
        arrby2[28] = -60;
        arrby2[29] = -34;
        arrby2[30] = -23;
        arrby2[31] = -53;
        arrby2[32] = 84;
        arrby2[33] = 123;
        arrby2[34] = -108;
        arrby2[35] = 50;
        arrby2[36] = -90;
        arrby2[37] = -62;
        arrby2[38] = 35;
        arrby2[39] = 61;
        arrby2[40] = -18;
        arrby2[41] = 76;
        arrby2[42] = -107;
        arrby2[43] = 11;
        arrby2[44] = 66;
        arrby2[45] = -6;
        arrby2[46] = -61;
        arrby2[47] = 78;
        arrby2[48] = 8;
        arrby2[49] = 46;
        arrby2[50] = -95;
        arrby2[51] = 102;
        arrby2[52] = 40;
        arrby2[53] = -39;
        arrby2[54] = 36;
        arrby2[55] = -78;
        arrby2[56] = 118;
        arrby2[57] = 91;
        arrby2[58] = -94;
        arrby2[59] = 73;
        arrby2[60] = 109;
        arrby2[61] = -117;
        arrby2[62] = -47;
        arrby2[63] = 37;
        arrby2[64] = 114;
        arrby2[65] = -8;
        arrby2[66] = -10;
        arrby2[67] = 100;
        arrby2[68] = -122;
        arrby2[69] = 104;
        arrby2[70] = -104;
        arrby2[71] = 22;
        arrby2[72] = -44;
        arrby2[73] = -92;
        arrby2[74] = 92;
        arrby2[75] = -52;
        arrby2[76] = 93;
        arrby2[77] = 101;
        arrby2[78] = -74;
        arrby2[79] = -110;
        arrby2[80] = 108;
        arrby2[81] = 112;
        arrby2[82] = 72;
        arrby2[83] = 80;
        arrby2[84] = -3;
        arrby2[85] = -19;
        arrby2[86] = -71;
        arrby2[87] = -38;
        arrby2[88] = 94;
        arrby2[89] = 21;
        arrby2[90] = 70;
        arrby2[91] = 87;
        arrby2[92] = -89;
        arrby2[93] = -115;
        arrby2[94] = -99;
        arrby2[95] = -124;
        arrby2[96] = -112;
        arrby2[97] = -40;
        arrby2[98] = -85;
        arrby2[100] = -116;
        arrby2[101] = -68;
        arrby2[102] = -45;
        arrby2[103] = 10;
        arrby2[104] = -9;
        arrby2[105] = -28;
        arrby2[106] = 88;
        arrby2[107] = 5;
        arrby2[108] = -72;
        arrby2[109] = -77;
        arrby2[110] = 69;
        arrby2[111] = 6;
        arrby2[112] = -48;
        arrby2[113] = 44;
        arrby2[114] = 30;
        arrby2[115] = -113;
        arrby2[116] = -54;
        arrby2[117] = 63;
        arrby2[118] = 15;
        arrby2[119] = 2;
        arrby2[120] = -63;
        arrby2[121] = -81;
        arrby2[122] = -67;
        arrby2[123] = 3;
        arrby2[124] = 1;
        arrby2[125] = 19;
        arrby2[126] = -118;
        arrby2[127] = 107;
        arrby2[128] = 58;
        arrby2[129] = -111;
        arrby2[130] = 17;
        arrby2[131] = 65;
        arrby2[132] = 79;
        arrby2[133] = 103;
        arrby2[134] = -36;
        arrby2[135] = -22;
        arrby2[136] = -105;
        arrby2[137] = -14;
        arrby2[138] = -49;
        arrby2[139] = -50;
        arrby2[140] = -16;
        arrby2[141] = -76;
        arrby2[142] = -26;
        arrby2[143] = 115;
        arrby2[144] = -106;
        arrby2[145] = -84;
        arrby2[146] = 116;
        arrby2[147] = 34;
        arrby2[148] = -25;
        arrby2[149] = -83;
        arrby2[150] = 53;
        arrby2[151] = -123;
        arrby2[152] = -30;
        arrby2[153] = -7;
        arrby2[154] = 55;
        arrby2[155] = -24;
        arrby2[156] = 28;
        arrby2[157] = 117;
        arrby2[158] = -33;
        arrby2[159] = 110;
        arrby2[160] = 71;
        arrby2[161] = -15;
        arrby2[162] = 26;
        arrby2[163] = 113;
        arrby2[164] = 29;
        arrby2[165] = 41;
        arrby2[166] = -59;
        arrby2[167] = -119;
        arrby2[168] = 111;
        arrby2[169] = -73;
        arrby2[170] = 98;
        arrby2[171] = 14;
        arrby2[172] = -86;
        arrby2[173] = 24;
        arrby2[174] = -66;
        arrby2[175] = 27;
        arrby2[176] = -4;
        arrby2[177] = 86;
        arrby2[178] = 62;
        arrby2[179] = 75;
        arrby2[180] = -58;
        arrby2[181] = -46;
        arrby2[182] = 121;
        arrby2[183] = 32;
        arrby2[184] = -102;
        arrby2[185] = -37;
        arrby2[186] = -64;
        arrby2[187] = -2;
        arrby2[188] = 120;
        arrby2[189] = -51;
        arrby2[190] = 90;
        arrby2[191] = -12;
        arrby2[192] = 31;
        arrby2[193] = -35;
        arrby2[194] = -88;
        arrby2[195] = 51;
        arrby2[196] = -120;
        arrby2[197] = 7;
        arrby2[198] = -57;
        arrby2[199] = 49;
        arrby2[200] = -79;
        arrby2[201] = 18;
        arrby2[202] = 16;
        arrby2[203] = 89;
        arrby2[204] = 39;
        arrby2[205] = -128;
        arrby2[206] = -20;
        arrby2[207] = 95;
        arrby2[208] = 96;
        arrby2[209] = 81;
        arrby2[210] = 127;
        arrby2[211] = -87;
        arrby2[212] = 25;
        arrby2[213] = -75;
        arrby2[214] = 74;
        arrby2[215] = 13;
        arrby2[216] = 45;
        arrby2[217] = -27;
        arrby2[218] = 122;
        arrby2[219] = -97;
        arrby2[220] = -109;
        arrby2[221] = -55;
        arrby2[222] = -100;
        arrby2[223] = -17;
        arrby2[224] = -96;
        arrby2[225] = -32;
        arrby2[226] = 59;
        arrby2[227] = 77;
        arrby2[228] = -82;
        arrby2[229] = 42;
        arrby2[230] = -11;
        arrby2[231] = -80;
        arrby2[232] = -56;
        arrby2[233] = -21;
        arrby2[234] = -69;
        arrby2[235] = 60;
        arrby2[236] = -125;
        arrby2[237] = 83;
        arrby2[238] = -103;
        arrby2[239] = 97;
        arrby2[240] = 23;
        arrby2[241] = 43;
        arrby2[242] = 4;
        arrby2[243] = 126;
        arrby2[244] = -70;
        arrby2[245] = 119;
        arrby2[246] = -42;
        arrby2[247] = 38;
        arrby2[248] = -31;
        arrby2[249] = 105;
        arrby2[250] = 20;
        arrby2[251] = 99;
        arrby2[252] = 85;
        arrby2[253] = 33;
        arrby2[254] = 12;
        arrby2[255] = 125;
        Si = arrby2;
        byte[] arrby3 = new byte[31];
        arrby3[1] = 1;
        arrby3[2] = 2;
        arrby3[3] = 4;
        arrby3[4] = 8;
        arrby3[5] = 16;
        arrby3[6] = 32;
        arrby3[7] = 64;
        arrby3[8] = -128;
        arrby3[9] = 27;
        arrby3[10] = 54;
        arrby3[11] = 108;
        arrby3[12] = -40;
        arrby3[13] = -85;
        arrby3[14] = 77;
        arrby3[15] = -102;
        arrby3[16] = 47;
        arrby3[17] = 94;
        arrby3[18] = -68;
        arrby3[19] = 99;
        arrby3[20] = -58;
        arrby3[21] = -105;
        arrby3[22] = 53;
        arrby3[23] = 106;
        arrby3[24] = -44;
        arrby3[25] = -77;
        arrby3[26] = 125;
        arrby3[27] = -6;
        arrby3[28] = -17;
        arrby3[29] = -59;
        arrby3[30] = -111;
        rcon = arrby3;
        int[] arrn = new int[4];
        arrn[1] = 1;
        arrn[2] = 2;
        arrn[3] = 3;
        row_shift = arrn;
        alog = new int[256];
        log = new int[256];
        AES.alog[0] = 1;
        int i = 1;
        while (i < 256) {
            int j = alog[i - 1] << 1 ^ alog[i - 1];
            if ((j & 256) != 0) {
                j ^= 283;
            }
            AES.alog[i] = j;
            ++i;
        }
        i = 1;
        while (i < 255) {
            AES.log[AES.alog[i]] = i++;
        }
    }

    public static void main(String[] args) {
        AES aes = new AES();
        aes.setKey("kWmHe8xIsDpfzK4d");
        String data = "Hello world, here is some sample text.";
        System.out.println("Original text : [" + data + "] [" + data.length() + " bytes]");
        String encrypted = aes.Encrypt(data);
        System.out.println("Encrypted text : [" + encrypted + "] [" + encrypted.length() + " bytes]");
        String hex = Util.toHEX(encrypted.getBytes()).replace(" ", "");
        System.out.println("Encrypted text (as hex) : [" + hex + "] [" + hex.length() + " bytes]");
        String unencrypted = aes.Decrypt(encrypted);
        System.out.println("Unencrypted text : [" + unencrypted + "] [" + unencrypted.length() + " bytes]");
    }

    public static int getRounds(int keySize) {
        switch (keySize) {
            case 16: {
                return 10;
            }
            case 24: {
                return 12;
            }
        }
        return 14;
    }

    static final int mul(int a, int b) {
        return a != 0 && b != 0 ? alog[(log[a & 255] + log[b & 255]) % 255] : 0;
    }

    public static void trace_static() {
        int j;
        System.out.print("AES Static Tablesn");
        System.out.print("S[] = n");
        int i = 0;
        while (i < 16) {
            j = 0;
            while (j < 16) {
                System.out.print(String.valueOf(Util.toHEX1(S[i * 16 + j])) + ", ");
                ++j;
            }
            System.out.println();
            ++i;
        }
        System.out.print("Si[] = n");
        i = 0;
        while (i < 16) {
            j = 0;
            while (j < 16) {
                System.out.print(String.valueOf(Util.toHEX1(Si[i * 16 + j])) + ", ");
                ++j;
            }
            System.out.println();
            ++i;
        }
        System.out.print("rcon[] = n");
        i = 0;
        while (i < 5) {
            j = 0;
            while (j < 6) {
                System.out.print(String.valueOf(Util.toHEX1(rcon[i * 6 + j])) + ", ");
                ++j;
            }
            System.out.println();
            ++i;
        }
        System.out.print("log[] = n");
        i = 0;
        while (i < 32) {
            j = 0;
            while (j < 8) {
                System.out.print(String.valueOf(Util.toHEX1(log[i * 8 + j])) + ", ");
                ++j;
            }
            System.out.println();
            ++i;
        }
        System.out.print("alog[] = n");
        i = 0;
        while (i < 32) {
            j = 0;
            while (j < 8) {
                System.out.print(String.valueOf(Util.toHEX1(alog[i * 8 + j])) + ", ");
                ++j;
            }
            System.out.println();
            ++i;
        }
    }

    public byte[] encrypt(byte[] plain) {
        int row;
        int k;
        byte[] a = new byte[16];
        byte[] ta = new byte[16];
        this.traceInfo = "";
        if (this.traceLevel > 0) {
            this.traceInfo = "encryptAES(" + Util.toHEX1(plain) + ")";
        }
        if (plain == null) {
            throw new IllegalArgumentException("Empty plaintext");
        }
        if (plain.length != 16) {
            throw new IllegalArgumentException("Incorrect plaintext length");
        }
        byte[] Ker = this.Ke[0];
        int i = 0;
        while (i < 16) {
            a[i] = (byte) (plain[i] ^ Ker[i]);
            ++i;
        }
        if (this.traceLevel > 2) {
            this.traceInfo = String.valueOf(this.traceInfo) + "n  R0 (Key = " + Util.toHEX1(Ker) + ")ntAK = " + Util.toHEX1(a);
        } else if (this.traceLevel > 1) {
            this.traceInfo = String.valueOf(this.traceInfo) + "n  R0 (Key = " + Util.toHEX1(Ker) + ")t = " + Util.toHEX1(a);
        }
        int r = 1;
        while (r < this.numRounds) {
            Ker = this.Ke[r];
            if (this.traceLevel > 1) {
                this.traceInfo = String.valueOf(this.traceInfo) + "n  R" + r + " (Key = " + Util.toHEX1(Ker) + ")t";
            }
            i = 0;
            while (i < 16) {
                ta[i] = S[a[i] & 255];
                ++i;
            }
            if (this.traceLevel > 2) {
                this.traceInfo = String.valueOf(this.traceInfo) + "ntSB = " + Util.toHEX1(ta);
            }
            i = 0;
            while (i < 16) {
                row = i % 4;
                k = (i + row_shift[row] * 4) % 16;
                a[i] = ta[k];
                ++i;
            }
            if (this.traceLevel > 2) {
                this.traceInfo = String.valueOf(this.traceInfo) + "ntSR = " + Util.toHEX1(a);
            }
            int col = 0;
            while (col < 4) {
                i = col * 4;
                ta[i] = (byte) (AES.mul(2, a[i]) ^ AES.mul(3, a[i + 1]) ^ a[i + 2] ^ a[i + 3]);
                ta[i + 1] = (byte) (a[i] ^ AES.mul(2, a[i + 1]) ^ AES.mul(3, a[i + 2]) ^ a[i + 3]);
                ta[i + 2] = (byte) (a[i] ^ a[i + 1] ^ AES.mul(2, a[i + 2]) ^ AES.mul(3, a[i + 3]));
                ta[i + 3] = (byte) (AES.mul(3, a[i]) ^ a[i + 1] ^ a[i + 2] ^ AES.mul(2, a[i + 3]));
                ++col;
            }
            if (this.traceLevel > 2) {
                this.traceInfo = String.valueOf(this.traceInfo) + "ntMC = " + Util.toHEX1(ta);
            }
            i = 0;
            while (i < 16) {
                a[i] = (byte) (ta[i] ^ Ker[i]);
                ++i;
            }
            if (this.traceLevel > 2) {
                this.traceInfo = String.valueOf(this.traceInfo) + "ntAK";
            }
            if (this.traceLevel > 1) {
                this.traceInfo = String.valueOf(this.traceInfo) + " = " + Util.toHEX1(a);
            }
            ++r;
        }
        Ker = this.Ke[this.numRounds];
        if (this.traceLevel > 1) {
            this.traceInfo = String.valueOf(this.traceInfo) + "n  R" + this.numRounds + " (Key = " + Util.toHEX1(Ker) + ")t";
        }
        i = 0;
        while (i < 16) {
            a[i] = S[a[i] & 255];
            ++i;
        }
        if (this.traceLevel > 2) {
            this.traceInfo = String.valueOf(this.traceInfo) + "ntSB = " + Util.toHEX1(a);
        }
        i = 0;
        while (i < 16) {
            row = i % 4;
            k = (i + row_shift[row] * 4) % 16;
            ta[i] = a[k];
            ++i;
        }
        if (this.traceLevel > 2) {
            this.traceInfo = String.valueOf(this.traceInfo) + "ntSR = " + Util.toHEX1(a);
        }
        i = 0;
        while (i < 16) {
            a[i] = (byte) (ta[i] ^ Ker[i]);
            ++i;
        }
        if (this.traceLevel > 2) {
            this.traceInfo = String.valueOf(this.traceInfo) + "ntAK";
        }
        if (this.traceLevel > 1) {
            this.traceInfo = String.valueOf(this.traceInfo) + " = " + Util.toHEX1(a) + "n";
        }
        if (this.traceLevel > 0) {
            this.traceInfo = String.valueOf(this.traceInfo) + " = " + Util.toHEX1(a) + "n";
        }
        return a;
    }

    public byte[] decrypt(byte[] cipher) {
        int row;
        int k;
        byte[] a = new byte[16];
        byte[] ta = new byte[16];
        this.traceInfo = "";
        if (this.traceLevel > 0) {
            this.traceInfo = "decryptAES(" + Util.toHEX1(cipher) + ")";
        }
        if (cipher == null) {
            throw new IllegalArgumentException("Empty ciphertext");
        }
        if (cipher.length != 16) {
            throw new IllegalArgumentException("Incorrect ciphertext length");
        }
        byte[] Kdr = this.Kd[0];
        int i = 0;
        while (i < 16) {
            a[i] = (byte) (cipher[i] ^ Kdr[i]);
            ++i;
        }
        if (this.traceLevel > 2) {
            this.traceInfo = String.valueOf(this.traceInfo) + "n  R0 (Key = " + Util.toHEX1(Kdr) + ")nt AK = " + Util.toHEX1(a);
        } else if (this.traceLevel > 1) {
            this.traceInfo = String.valueOf(this.traceInfo) + "n  R0 (Key = " + Util.toHEX1(Kdr) + ")t = " + Util.toHEX1(a);
        }
        int r = 1;
        while (r < this.numRounds) {
            Kdr = this.Kd[r];
            if (this.traceLevel > 1) {
                this.traceInfo = String.valueOf(this.traceInfo) + "n  R" + r + " (Key = " + Util.toHEX1(Kdr) + ")t";
            }
            i = 0;
            while (i < 16) {
                row = i % 4;
                k = (i + 16 - row_shift[row] * 4) % 16;
                ta[i] = a[k];
                ++i;
            }
            if (this.traceLevel > 2) {
                this.traceInfo = String.valueOf(this.traceInfo) + "ntISR = " + Util.toHEX1(ta);
            }
            i = 0;
            while (i < 16) {
                a[i] = Si[ta[i] & 255];
                ++i;
            }
            if (this.traceLevel > 2) {
                this.traceInfo = String.valueOf(this.traceInfo) + "ntISB = " + Util.toHEX1(a);
            }
            i = 0;
            while (i < 16) {
                ta[i] = (byte) (a[i] ^ Kdr[i]);
                ++i;
            }
            if (this.traceLevel > 2) {
                this.traceInfo = String.valueOf(this.traceInfo) + "nt AK = " + Util.toHEX1(ta);
            }
            int col = 0;
            while (col < 4) {
                i = col * 4;
                a[i] = (byte) (AES.mul(14, ta[i]) ^ AES.mul(11, ta[i + 1]) ^ AES.mul(13, ta[i + 2]) ^ AES.mul(9, ta[i + 3]));
                a[i + 1] = (byte) (AES.mul(9, ta[i]) ^ AES.mul(14, ta[i + 1]) ^ AES.mul(11, ta[i + 2]) ^ AES.mul(13, ta[i + 3]));
                a[i + 2] = (byte) (AES.mul(13, ta[i]) ^ AES.mul(9, ta[i + 1]) ^ AES.mul(14, ta[i + 2]) ^ AES.mul(11, ta[i + 3]));
                a[i + 3] = (byte) (AES.mul(11, ta[i]) ^ AES.mul(13, ta[i + 1]) ^ AES.mul(9, ta[i + 2]) ^ AES.mul(14, ta[i + 3]));
                ++col;
            }
            if (this.traceLevel > 2) {
                this.traceInfo = String.valueOf(this.traceInfo) + "ntIMC";
            }
            if (this.traceLevel > 1) {
                this.traceInfo = String.valueOf(this.traceInfo) + " = " + Util.toHEX1(a);
            }
            ++r;
        }
        Kdr = this.Kd[this.numRounds];
        if (this.traceLevel > 1) {
            this.traceInfo = String.valueOf(this.traceInfo) + "n  R" + this.numRounds + " (Key = " + Util.toHEX1(Kdr) + ")t";
        }
        i = 0;
        while (i < 16) {
            row = i % 4;
            k = (i + 16 - row_shift[row] * 4) % 16;
            ta[i] = a[k];
            ++i;
        }
        if (this.traceLevel > 2) {
            this.traceInfo = String.valueOf(this.traceInfo) + "ntISR = " + Util.toHEX1(a);
        }
        i = 0;
        while (i < 16) {
            ta[i] = Si[ta[i] & 255];
            ++i;
        }
        if (this.traceLevel > 2) {
            this.traceInfo = String.valueOf(this.traceInfo) + "ntISB = " + Util.toHEX1(a);
        }
        i = 0;
        while (i < 16) {
            a[i] = (byte) (ta[i] ^ Kdr[i]);
            ++i;
        }
        if (this.traceLevel > 2) {
            this.traceInfo = String.valueOf(this.traceInfo) + "nt AK";
        }
        if (this.traceLevel > 1) {
            this.traceInfo = String.valueOf(this.traceInfo) + " = " + Util.toHEX1(a) + "n";
        }
        if (this.traceLevel > 0) {
            this.traceInfo = String.valueOf(this.traceInfo) + " = " + Util.toHEX1(a) + "n";
        }
        return a;
    }

    public void setKey(byte[] key) {
        int BC = 4;
        int Klen = key.length;
        int Nk = Klen / 4;
        this.traceInfo = "";
        if (this.traceLevel > 0) {
            this.traceInfo = "setKey(" + Util.toHEX1(key) + ")n";
        }
        if (key == null) {
            // empty if block
        }
        if (key.length != 16 && key.length != 24 && key.length != 32) {
            throw new IllegalArgumentException("Incorrect key length");
        }
        this.numRounds = AES.getRounds(Klen);
        int ROUND_KEY_COUNT = (this.numRounds + 1) * 4;
        byte[] w0 = new byte[ROUND_KEY_COUNT];
        byte[] w1 = new byte[ROUND_KEY_COUNT];
        byte[] w2 = new byte[ROUND_KEY_COUNT];
        byte[] w3 = new byte[ROUND_KEY_COUNT];
        this.Ke = new byte[this.numRounds + 1][16];
        this.Kd = new byte[this.numRounds + 1][16];
        int i = 0;
        int j = 0;
        while (i < Nk) {
            w0[i] = key[j++];
            w1[i] = key[j++];
            w2[i] = key[j++];
            w3[i] = key[j++];
            ++i;
        }
        i = Nk;
        while (i < ROUND_KEY_COUNT) {
            byte t0 = w0[i - 1];
            byte t1 = w1[i - 1];
            byte t2 = w2[i - 1];
            byte t3 = w3[i - 1];
            if (i % Nk == 0) {
                byte old0 = t0;
                t0 = (byte) (S[t1 & 255] ^ rcon[i / Nk]);
                t1 = S[t2 & 255];
                t2 = S[t3 & 255];
                t3 = S[old0 & 255];
            } else if (Nk > 6 && i % Nk == 4) {
                t0 = S[t0 & 255];
                t1 = S[t1 & 255];
                t2 = S[t2 & 255];
                t3 = S[t3 & 255];
            }
            w0[i] = (byte) (w0[i - Nk] ^ t0);
            w1[i] = (byte) (w1[i - Nk] ^ t1);
            w2[i] = (byte) (w2[i - Nk] ^ t2);
            w3[i] = (byte) (w3[i - Nk] ^ t3);
            ++i;
        }
        int r = 0;
        i = 0;
        while (r < this.numRounds + 1) {
            j = 0;
            while (j < 4) {
                this.Ke[r][4 * j] = w0[i];
                this.Ke[r][4 * j + 1] = w1[i];
                this.Ke[r][4 * j + 2] = w2[i];
                this.Ke[r][4 * j + 3] = w3[i];
                this.Kd[this.numRounds - r][4 * j] = w0[i];
                this.Kd[this.numRounds - r][4 * j + 1] = w1[i];
                this.Kd[this.numRounds - r][4 * j + 2] = w2[i];
                this.Kd[this.numRounds - r][4 * j + 3] = w3[i];
                ++i;
                ++j;
            }
            ++r;
        }
        if (this.traceLevel > 3) {
            this.traceInfo = String.valueOf(this.traceInfo) + "  Encrypt Round keys:n";
            r = 0;
            while (r < this.numRounds + 1) {
                this.traceInfo = String.valueOf(this.traceInfo) + "  R" + r + "t = " + Util.toHEX1(this.Ke[r]) + "n";
                ++r;
            }
            this.traceInfo = String.valueOf(this.traceInfo) + "  Decrypt Round keys:n";
            r = 0;
            while (r < this.numRounds + 1) {
                this.traceInfo = String.valueOf(this.traceInfo) + "  R" + r + "t = " + Util.toHEX1(this.Kd[r]) + "n";
                ++r;
            }
        }
    }

    public static void self_test(String hkey, String hplain, String hcipher, int lev) {
        byte[] key = Util.hex2byte(hkey);
        byte[] plain = Util.hex2byte(hplain);
        byte[] cipher = Util.hex2byte(hcipher);
        AES testAES = new AES();
        testAES.traceLevel = lev;
        testAES.setKey(key);
        System.out.print(testAES.traceInfo);
        byte[] result = testAES.encrypt(plain);
        System.out.print(testAES.traceInfo);
        if (Arrays.equals(result, cipher)) {
            System.out.print("Test OKn");
        } else {
            System.out.print("Test Failed. Result was " + Util.toHEX(result) + "n");
        }
        result = testAES.decrypt(cipher);
        System.out.print(testAES.traceInfo);
        if (Arrays.equals(result, plain)) {
            System.out.print("Test OKn");
        } else {
            System.out.print("Test Failed. Result was " + Util.toHEX(result) + "n");
        }
        System.out.println();
    }

    public static String static_byteArrayToString(byte[] data) {
        String res = "";
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < data.length) {
            int n = data[i];
            if (n < 0) {
                n += 256;
            }
            sb.append((char) n);
            ++i;
        }
        res = sb.toString();
        return res;
    }

    public static byte[] static_stringToByteArray(String s) {
        byte[] temp = new byte[s.length()];
        int i = 0;
        while (i < s.length()) {
            temp[i] = (byte) s.charAt(i);
            ++i;
        }
        return temp;
    }

    public static String static_intArrayToString(int[] t) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < t.length) {
            sb.append((char) t[i]);
            ++i;
        }
        return sb.toString();
    }

    public String _cryptAll(String data, int mode) {
        AES aes = this;
        if (data.length() / 16 > data.length() / 16) {
            int rest = data.length() - data.length() / 16 * 16;
            int i = 0;
            while (i < rest) {
                data = String.valueOf(data) + " ";
                ++i;
            }
        }
        int nParts = data.length() / 16;
        byte[] res = new byte[data.length()];
        String partStr = "";
        byte[] partByte = new byte[16];
        int p = 0;
        while (p < nParts) {
            partStr = data.substring(p * 16, p * 16 + 16);
            partByte = AES.static_stringToByteArray(partStr);
            if (mode == 1) {
                partByte = aes.encrypt(partByte);
            }
            if (mode == 2) {
                partByte = aes.decrypt(partByte);
            }
            int b = 0;
            while (b < 16) {
                res[p * 16 + b] = partByte[b];
                ++b;
            }
            ++p;
        }
        return AES.static_byteArrayToString(res);
    }

    public String Encrypt(String data) {
        while (data.length() % 32 != 0) {
            data = String.valueOf(data) + " ";
        }
        return this._cryptAll(data, 1);
    }

    public String Decrypt(String data) {
        return this._cryptAll(data, 2).trim();
    }

    public void setKey(String key) {
        this.setKey(AES.static_stringToByteArray(key));
    }

    private static final class Util {
        public static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

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

}
