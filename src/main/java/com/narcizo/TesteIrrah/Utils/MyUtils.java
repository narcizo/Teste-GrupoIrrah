package com.narcizo.TesteIrrah.Utils;

public class MyUtils {
    public static String validatePhoneNumber(String phoneNumber){
        // Aceita input de celular (xx)9xxxx-xxxx
        if (phoneNumber.matches("^\\([1-9]{2}\\)[9]{0,1}[5-9]{1}[0-9]{3}\\-[0-9]{4}$"))
            return phoneNumber;

        return "";
    }

    public static String validateCpf(String cpf){
        // Aceita input de cpf xxx.xxx.xxx-xx
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}"))
            return "";

        int[] digits = extractDigits(11, cpf);

        int firstVerifier = calculateVerifierDigit(digits, 9, 10);
        int secondVerifier = calculateVerifierDigit(digits, 10, 11);

        if (digits[9] == firstVerifier && digits[10] == secondVerifier)
            return String.format("%03d.%03d.%03d-%02d",
                    digits[0], digits[1], digits[2],
                    digits[9] * 10 + digits[10]);

        return "";
    }

    public static String validateCnpj(String cnpj){
        // Aceita input de cpf xx.xxx.xxx/xxxx-xx
        char dig13, dig14;

        String cnpjStriped = cnpj.replaceAll("[^0-9]", "");

        dig13 = calculateDigit(11, cnpjStriped);
        dig14 = calculateDigit(12, cnpjStriped);

        if ((dig13 == cnpjStriped.charAt(12)) && (dig14 == cnpjStriped.charAt(13)))
            return cnpj;
        return "";
    }

    private static int[] extractDigits(int size, String number){
        int[] digits = new int[size];
        for (int i = 0; i < size; i++) {
            digits[i] = number.charAt(i) - '0';
        }

        return digits;
    }

    private static int calculateVerifierDigit(int[] digits, int length, int weight) {
        int sum = 0;
        for (int i = 0; i < length; i++) {
            sum += digits[i] * weight;
            weight = (weight == 2) ? 9 : weight - 1;
        }
        int remainder = sum % 11;
        return (remainder < 2) ? 0 : 11 - remainder;
    }

    private static char calculateDigit(int length, String s){
        int sm, r, num, peso;
        sm = 0;
        peso = 2;
        for (int i=length; i>=0; i--) {
            num = (int)(s.charAt(i) - 48);
            sm += (num * peso);
            peso++;
            if (peso == 10)
                peso = 2;
        }

        r = sm % 11;
        if ((r == 0) || (r == 1))
            return '0';
        return (char)((11-r) + 48);
    }
}
