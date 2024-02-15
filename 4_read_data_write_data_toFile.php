<?php
// Input file path
$inputFilePath = 'input.txt';
// Output file path
$outputFilePath = 'output.txt';
// Check if the input file exists
if (file_exists($inputFilePath)) {
    // Read data from the input file
    $data = file_get_contents($inputFilePath);
    // Check if the data is successfully read
    if ($data !== false) {
        // Write data to the output file
        $writeSuccess = file_put_contents($outputFilePath, $data);
        // Check if writing to the output file is successful 
        if ($writeSuccess !== false) {
            echo "Data successfully copied from '$inputFilePath' to '$outputFilePath'.";
        } else {
            echo "Error writing to '$outputFilePath'.";
        }
    } else {
        echo "Error reading data from '$inputFilePath'.";
    }
} else {
    echo "Input file '$inputFilePath' does not exist.";
}
