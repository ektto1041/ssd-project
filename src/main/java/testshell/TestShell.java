package testshell;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;

public class TestShell {
    String command;
    String jarPath = "ssd.jar";
    Process process;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public TestShell(){
        try {
            System.out.println("명령어에 대한 설명이 필요하면 help를 입력하십시오.");

            while (true) {
                this.command = br.readLine().trim();

                if (command.equals("testapp1")) {
                    System.out.println("test1 실행. 채워넣을 데이터를 입력해주세요.");
                    testapp1();
                } else if (command.equals("testapp2")) {
                    testapp2();
                } else if (command.equals("exit")) {
                    System.out.println("bye");
                    break;
                } else if (command.equals("help")) {
                    System.out.println("사용 가능 명령어");
                    System.out.println("- write [LBA num] [value] 데이터를 입력합니다.");
                    System.out.println("- read [LBA num] 데이터를 읽어옵니다.");
                    System.out.println("- exit 테스트를 종료합니다.");
                    System.out.println("- help 명령어를 표시합니다.");
                    System.out.println("- fullwrite 모든 데이터를 입력합니다.");
                    System.out.println("- fullread 모든 데이터를 읽어옵니다.");

                } else if (command.contains("write") && !command.contains("fullwrite")) {
                    String[] writeTemp = command.split(" ");
                    String lba = writeTemp[1];
                    String value = writeTemp[2];
                    writeCommand(lba, value);
                } else if (command.contains("read") && !command.equals("fullread")){
                    String[] readTemp = command.split(" ");
                    String lba = readTemp[1];
                    readCommand(lba);
                } else if (command.contains("fullwrite")){
                    fullwrite();
                } else if (command.equals("fullread")){
                    fullread();
                }

                else {
                    System.out.println("INVALID COMMAND");
                    continue;
                }
            }
        } catch (IOException e) {
            System.err.println("IOException occurred: " + e.getMessage());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public void testapp2() throws IOException, URISyntaxException {
        String value = "0xAAAABBBB";
        for (int i=0; i<30; i++){
            for (int j=0; j<5; j++){
                String lba = Integer.toString(j);
                writeCommand(lba,value);
            }
        }
        String second = "0x77777777";
        for (int i=0; i<5; i++){
            String lba = Integer.toString(i);
            writeCommand(lba,second);
        }
        for (int i=0; i<5; i++){
            String lba = Integer.toString(i);
            readCommand(lba);
        }
    }

    public void testapp1() throws IOException, URISyntaxException {
        fullwrite();
        fullread();
    }

    public void writeCommand(String lba, String value) throws IOException, URISyntaxException {
        String path = TestShell.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "ssd.jar";
        System.out.println(path);
        path = path.substring(1,path.length());
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", path,"W",lba,value);
        //processBuilder.directory(new File("C:\\ssd_project\\ssd-project\\src\\main\\java\\testshell"));
        Process ps = processBuilder.start();


        String line = br.readLine().trim();
        br.close();
        System.out.println(line);
        ps.destroy();
    }
    public void readCommand(String lba) throws IOException, URISyntaxException {
        String path = TestShell.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "ssd.jar";
        System.out.println(path);
        path = path.substring(1,path.length());
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", path,"R",lba);
//        processBuilder.directory(new File("C:\\ssd_project\\ssd-project\\src\\main\\java\\testshell"));
        Process ps = processBuilder.start();
        String line = br.readLine().trim();
        String line2 = br.readLine().trim();
        System.out.println(line);
        System.out.println(line2);
        ps.destroy(); // 프로세스 종료
    }
    public void fullread() throws IOException, URISyntaxException {
        for (int i=0; i<30; i++){
            String lba = Integer.toString(i);
            readCommand(lba);
        }
    }
    public void fullwrite() throws IOException, URISyntaxException {

        String value = br.readLine().trim();
        for (int i=0; i<30; i++){
            String lba = Integer.toString(i);
            writeCommand(lba, value);
        }
    }
}
