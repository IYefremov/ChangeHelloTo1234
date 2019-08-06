//   1. Написать программу, которая считает текстовый файл,
//   заменит в нем все слова “Hello” на “1234” и запишет изменения в тот-же файл.


import java.io.*;
import java.util.Arrays;

public class ChangeHello {

    public static byte[] changeArray(byte[] sourceFile, byte[] firstWord, byte[] secondWord) throws IOException {
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        while (i < sourceFile.length) {
            if (Arrays.equals(Arrays.copyOfRange(sourceFile, i, firstWord.length + i), firstWord)) {// если набор битов искомого слова равен набору битов из текущего ранга байтов в цикле
                byteArrayOutputStream.write(secondWord);             // записываем второе слово
                i += firstWord.length;                               // перскакиваем на длину певого слова для поиска следуещего симовла
            } else {
                byteArrayOutputStream.write(sourceFile[i]);          // иначе записываем текущий символ
                i++;
            }
        }
        return byteArrayOutputStream.toByteArray();                  // возвращаем отредактированный массив
    }

    public static void main(String[] args) throws IOException {

        String ourFile = "G:\\1.txt";
        String findWord = "Hello";
        String toReplaceWord = "1234";

        byte[] firstWordByte = findWord.getBytes(); //
        byte[] secondWordByte = toReplaceWord.getBytes();

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;

        try {
            fileInputStream = new FileInputStream(ourFile);
        } catch (FileNotFoundException e) {
            System.out.println(e.getCause());
        }

        int availChars;
        byte[] buf = new byte[fileInputStream.available()]; // объявляем массив с размером байтов доступным для данного файла
        try {
            do {
                availChars = fileInputStream.read(buf);
                if(availChars == 0) System.out.println("File is empty");
            } while (availChars > 0);      // считіваем в буфер до последнего байта
        } finally {
            fileInputStream.close();
        }

        System.out.println(new String(buf) + "\n");

        try {
            fileOutputStream = new FileOutputStream(ourFile);
        } catch (FileNotFoundException e) {
            System.out.println(e.getCause());
        }
        try {
            fileOutputStream.write(changeArray(buf, firstWordByte, secondWordByte));
            System.out.println(new String(changeArray(buf, firstWordByte, secondWordByte)));
        } finally {
            fileOutputStream.close();
        }

    }
}





