
<?php

$myString = "Hello, World!";

$lengthOfString = strlen($myString);
echo "<br>Length of the string: $lengthOfString";

$wordCount = str_word_count($myString);
echo "<br>Number of words in the string: $wordCount";

$reversedString = strrev($myString);
echo "<br>Reversed string: $reversedString";

$searchString = "World";
if (strpos($myString, $searchString) !== false) {
    echo "<br>String '$searchString' found in the given string.";
} else {
    echo "<br>String '$searchString' not found in the given string.";
}

?>
