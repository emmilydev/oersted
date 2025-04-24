package dev.emmily.oersted.circuit.dsl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.nio.file.Files;

public class TableLoader {
  private static final FilenameFilter CSV_FILE = (dir, name) -> name.endsWith(".csv");
  private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder().setSkipHeaderRecord(true).build();

  private final File sourceFolder;
  private final TableSelectionStrategy tableSelectionStrategy;

  public TableLoader(File sourceFolder,
                     TableSelectionStrategy tableSelectionStrategy) {
    this.sourceFolder = sourceFolder;
    this.tableSelectionStrategy = tableSelectionStrategy;
  }

  public void loadTables() throws IOException {
    File[] tables = sourceFolder.listFiles(CSV_FILE);

    if (tables == null) {
      throw new FileNotFoundException("Couldn't find table files");
    }

    for (File table : tables) {
      try (var reader = Files.newBufferedReader(table.toPath()); var parser = CSV_FORMAT.parse(reader)) {

      }
    }
  }

  private Table loadTable(File table) throws IOException {
    try (var reader = Files.newBufferedReader(table.toPath()); var parser = CSV_FORMAT.parse(reader)) {
      return new Table(
        table.getName().replace(".csv", ""),
        "context xd", rule?, table,
        parser.stream().toList()
      );
    }
  }
}
