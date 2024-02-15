<?php
$a1 = [1, 3, 15, 7, 5];
$a2 = [4, 3, 20, 1, 6];
$num = array_merge($a1, $a2);
array_multisort($num, SORT_DESC, SORT_NUMERIC);
print_r($num);

?> 