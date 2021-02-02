/*  Copyright (C) 2021 Sergey Demidov   */

package legacy.legacyPatcher;

import java.io.*;

public class IO {

    public String[] read(String path){
        BufferedReader br = null;
        String line = null;
        String[] lines = null;

        if(fileNameValidation(path)){
            //File file = new File(path);
            try{
                InputStream in = getClass().getClassLoader().getResourceAsStream(path);
                br = new BufferedReader(new InputStreamReader(in));
                Integer lineCount = null;

                if((line = br.readLine()) != null){
                    lineCount = getLineCount_fromString(line);
                    if(lineCount != null){
                        lines = new String[lineCount];

                        for(int i = 0; (line = br.readLine()) != null; i++){
                            lines[i] = line;
                        }

                        System.out.println("[INFO] reading from " + path + " done");
                    } else {
                        System.out.println("[ERROR] 'IO.read' " +  path + " has no valid line count");
                    }
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.err.println("[ERROR] 'IO.read' failed");
        }

        return lines;
    }



    public static boolean fileNameValidation(String fileName){
        boolean valid = true;

        if(fileName == null) {
            System.err.println("[ERROR] 'IO.fileNameValidation' fileName is null");
            valid = false;
        }
        if((fileName == "") || (fileName == " ")) {
            System.err.println("[ERROR] 'IO.fileNameValidation' fileName is empty");
            valid = false;
        }
        if(!fileName.contains(".txt")) {
            System.err.println("[ERROR] 'IO.fileNameValidation' " + fileName + " is not in txt format");
            valid = false;
        }

        return valid;
    }

    private static Integer getLineCount_fromString(String firstLine){
        Integer number = null;
        boolean valid = true;

        try {
            number = Integer.parseInt(firstLine);
            if(number < 0){
                valid = false;
            }

        } catch (NumberFormatException e){
            e.printStackTrace();
            valid = false;
        }


        if(valid){
            return number;
        } else {
            return null;
        }
    }


}
