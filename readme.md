# Oersted

Oersted is a comprehensive tool designed for electricians, based on the **Reglamento de Baja Tensión de UTE** (Uruguay). It provides a suite of electrical calculations and design utilities to streamline and ensure compliance with low-voltage electrical installations.

## Features

Oersted includes the following functionalities to support electrical design and calculations:

- **Grounding System Calculation**  
  Computes grounding system parameters.  
  *Note*: Currently includes a simplified version. Full implementation of Wenner, Dwight, and Sunde methods is pending.

- **Conductor Sizing Calculation**  
  Determines the cross-sectional area for phase, neutral, and protective earth (PE) conductors, considering:
  - Voltage drop
  - Maximum admissible current
  - Mechanical strength

- **Conduit Sizing Calculation**  
  Calculates the appropriate dimensions and types of conduits for electrical installations.

- **Luminotechnics Calculations**  
  Supports lighting design, including illuminance levels, fixture placement, and energy efficiency calculations.

- **Single-Phase and Three-Phase Transformer Calculation**  
  Computes transformer sizing and parameters for both single-phase and three-phase systems.

- **Motor Protection Calculation**  
  Designs protection systems for motors, including:
  - Contactors
  - Motor circuit breakers
  - Thermal relays
  - Variable frequency drives (VFDs)

- **Protection System Calculation**  
  Calculates parameters for circuit breakers, fuses, and other protective devices to ensure safety and compliance.

- **Maximum Admissible Current Table**  
  Provides a reference table for maximum admissible currents based on conductor types and installation conditions.

- **Short-Circuit Current (Icc) Calculation**  
  Computes short-circuit currents to ensure proper selection of protective devices and system robustness.

- **Power Factor Correction Calculation**  
  Determines capacitor bank sizing to improve power factor and reduce reactive power penalties.

- **Load Survey**  
  Performs a census of electrical loads to assess total demand and balance the system.

- **Lightning Protection Calculation**  
  Designs lightning protection systems using:
  - Rolling sphere method
  - Protection angle method