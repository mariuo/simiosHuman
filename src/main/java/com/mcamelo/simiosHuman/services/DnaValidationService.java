package com.mcamelo.simiosHuman.services;

import com.mcamelo.simiosHuman.dtos.DnaResponse;
import com.mcamelo.simiosHuman.entities.Dna;
import com.mcamelo.simiosHuman.entities.enums.DnaType;
import com.mcamelo.simiosHuman.repositories.DnaRepository;
import com.mcamelo.simiosHuman.services.exceptions.DatabaseException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class DnaValidationService {
    private static final Set<Character> LETTERS = Set.of('A', 'G', 'T', 'C');

    @Autowired
    Logger log;

    private final DnaRepository dnaRepository;

    public DnaValidationService(DnaRepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public char[][] convertToChar(String[] dnaArray) {
        int lines = dnaArray.length;
        int cols = dnaArray[0].length();
        char[][] matrizDna = new char[lines][cols];
        for (int x = 0; x < lines; x++) {
            matrizDna[x] = dnaArray[x].toUpperCase().toCharArray();
        }
        return matrizDna;
    }

    public String convertToString(char[][] matrizDna) {
        int rows = matrizDna.length;
        int cols = matrizDna[0].length;
        StringBuilder dnaString = new StringBuilder("[");

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                dnaString.append(matrizDna[x][y]);
            }
            if (x < rows - 1) {
                dnaString.append(", ");
            }
        }
        dnaString.append("]");
        return dnaString.toString();
    }

    public boolean isMatrixNN(String[] dna) {
        int lines = dna.length;
        for (String s : dna) {
            if (s.length() != lines) {
                log.info("Info: is not matrix linear.");
                return false;
            }
        }
        for (String s : dna) {
            for (char c : s.toCharArray()) {
                if (!LETTERS.contains(Character.toUpperCase(c))) {
                    log.info("Info: is not matrix DNA.");
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkDnaMatrix(char[][] matrix) {
        int lines = matrix.length;
        int seqLength = 4;  // length of the sequence to find

        AtomicBoolean found = new AtomicBoolean(false);

        // Run checks in parallel for large matrices
        if (lines > 3) {
            Thread horizontalThread = new Thread(() -> checkHorizontal(matrix, lines, seqLength, found));
            Thread verticalThread = new Thread(() -> checkVertical(matrix, lines, seqLength, found));
            Thread diagonalThread = new Thread(() -> checkDiagonal(matrix, lines, seqLength, found));
            log.info("Checking: ");
            horizontalThread.start();
            verticalThread.start();
            diagonalThread.start();

            try {
                horizontalThread.join();
                verticalThread.join();
                diagonalThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return found.get();
        } else {
            return checkHorizontal(matrix, lines, seqLength, found) ||
                    checkVertical(matrix, lines, seqLength, found) ||
                    checkDiagonal(matrix, lines, seqLength, found);
        }
    }

    private boolean checkHorizontal(char[][] matrix, int lines, int seqLength, AtomicBoolean found) {
        for (int x = 0; x < lines && !found.get(); x++) {
            for (int y = 0; y <= lines - seqLength && !found.get(); y++) {
                if (isValidSequence(matrix, x, y, 0, 1, seqLength)) {
                    found.set(true);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkVertical(char[][] matrix, int lines, int seqLength, AtomicBoolean found) {
        for (int y = 0; y < lines && !found.get(); y++) {
            for (int x = 0; x <= lines - seqLength && !found.get(); x++) {
                if (isValidSequence(matrix, x, y, 1, 0, seqLength)) {
                    found.set(true);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkDiagonal(char[][] matrix, int lines, int seqLength, AtomicBoolean found) {
        for (int x = 0; x <= lines - seqLength && !found.get(); x++) {
            for (int y = 0; y <= lines - seqLength && !found.get(); y++) {
                if (isValidSequence(matrix, x, y, 1, 1, seqLength) ||
                        isValidSequence(matrix, x, y + seqLength - 1, 1, -1, seqLength)) {
                    found.set(true);
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidSequence(char[][] matrix, int x, int y, int dx, int dy, int seqLength) {
        char start = matrix[x][y];
        for (int i = 1; i < seqLength; i++) {
            if (matrix[x + i * dx][y + i * dy] != start) {
                return false;
            }
        }
        return true;
    }

    public void view(char[][] matrix) {
        for (char[] row : matrix) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
    public boolean isSimian(String[] dna) {
        char[][] matrix = convertToChar(dna);

        Dna entity = new Dna();
        entity.setSequence(convertToString(matrix));

        try {
            if (checkDnaMatrix(matrix)) {
                // Insert DNA in database as Simios
                entity.setDnaType(DnaType.SIMIOS);
                dnaRepository.save(entity);
                log.info("Saved DNA: " + DnaType.SIMIOS);
                return true;
            } else {
                entity.setDnaType(DnaType.HUMAN);
                dnaRepository.save(entity);
                log.info("Saved DNA: " + DnaType.HUMAN);
                return false;
            }
        }catch (DatabaseException e){
            throw new DatabaseException("Data base: " + e);
        }
    }
    public List<DnaResponse> findAll(){
        List<Dna> list = dnaRepository.findAll();
        return list.stream().map(x -> new DnaResponse(x.getId(), x.getSequence(), x.getDnaType())).collect(Collectors.toList());
    }
}