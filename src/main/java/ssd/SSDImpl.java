package ssd;

import fileio.FileIO;
import java.util.Arrays;

public class SSDImpl implements SSD {

    FileIO io;
    int[] lba = new int[100];

    public SSDImpl(){
        Arrays.fill(this.lba, -1);
    };

    public SSDImpl(FileIO io){
        this.io = io;
        Arrays.fill(this.lba, -1);
    };

    @Override
    public void write(int index, String value) {
        this.lba[index] = Integer.decode(value);
        String tot = "";

        for(int i = 0; i < lba.length; i++){
            tot += Integer.toHexString(lba[i]) + (i != 99 ? "|" : "");
        }

        System.out.println(tot);
//        io.write("nand.txt", tot);
    }

    @Override
    public String read(int index) {
        return "";
    }
}
