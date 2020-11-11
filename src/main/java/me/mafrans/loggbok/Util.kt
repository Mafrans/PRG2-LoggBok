package me.mafrans.loggbok

import javax.xml.bind.DatatypeConverter




class Util {
    companion object {
        fun toRoman(number: Int): String {
            return "I".repeat(number)
                    .replace("IIIII", "V")
                    .replace("IIII", "IV")
                    .replace("VV", "X")
                    .replace("VIV", "IX")
                    .replace("XXXXX", "L")
                    .replace("XXXX", "XL")
                    .replace("LL", "C")
                    .replace("LXL", "XC")
                    .replace("CCCCC", "D")
                    .replace("CCCC", "CD")
                    .replace("DD", "M")
                    .replace("DCD", "CM");
        }

        fun toHexString(array: ByteArray?): String? {
            return DatatypeConverter.printHexBinary(array)
        }

        fun toByteArray(s: String?): ByteArray? {
            return DatatypeConverter.parseHexBinary(s)
        }
    }
}