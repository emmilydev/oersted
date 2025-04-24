package dev.emmily.oersted.circuit.dsl;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.util.List;

/**
 * Represents a Maximum Admissible Current Table of UTE's RBT.
 */
public record Table(String id,
                    String context,
                    TableRule rule,
                    File source,
                    List<CSVRecord> csvRecords) {}
