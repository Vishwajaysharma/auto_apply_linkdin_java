package com.vishwajay.jobbot.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ExcelQuestionAnswerService {
    private final String filePath = System.getProperty("user.dir") + "/qa_pairs.xlsx";
    private final Map<String, String> qaMap = new HashMap<>();

    public ExcelQuestionAnswerService() {
        loadQAFromExcel();
    }

    private void loadQAFromExcel() {
        File file = new File(filePath);
        if (!file.exists()) return;

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell qCell = row.getCell(0);
                Cell aCell = row.getCell(1);
                if (qCell != null && aCell != null) {
                    qaMap.put(qCell.getStringCellValue(), aCell.getStringCellValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveQAtoExcel() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("QA");

            int rowIndex = 0;
            for (Map.Entry<String, String> entry : qaMap.entrySet()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getOrAskAnswer(String question) {
        if (qaMap.containsKey(question)) {
            System.out.println("Answering from Excel: " + qaMap.get(question));
            return qaMap.get(question);
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("New question: " + question + "\nPlease provide an answer: ");
            String answer = scanner.nextLine();

            qaMap.put(question, answer);
            saveQAtoExcel();

            System.out.println("Answer stored in Excel.");
            return answer;
        }
    }
}
