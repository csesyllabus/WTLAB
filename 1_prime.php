<!-- Write a PHP script to print prime numbers between 1 – 50. -->
<?php
$number = 2;
while ($number < 100) {
    $div_count = 0;
    for ($i = 1; $i <= $number; $i++) {
        if ($number % $i == 0) {
            $div_count++;
        }
    }
    if ($div_count < 3) {
        echo $number . " , ";
    }
    $number = $number + 1;
}
?>
<!-- OUTPUT : 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47 -->