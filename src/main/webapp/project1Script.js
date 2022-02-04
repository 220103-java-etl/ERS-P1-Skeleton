
let p = document.getElementById('P1Par');
console.log(p);


function changePar() {

let p = document.getElementById('changeMe');
console.log("Button has been pressed");
p.innerHTML = "HUGE SUCCESS";
}

// the selector will match all input controls of type :checkbox
// and attach a click event handler
function onlyOne(checkbox) {
    var checkboxes = document.getElementByName('checkbox')
    checkboxes.forEach((item) => {
        if (item !== checkbox) item.checked = false
    })
}

function display_data($data) {
$output = '<table>';
foreach($data as $key => $var) {
    $output .= '<tr>';
    foreach($var as $k => $v) {
        if ($key === 0) {
            $output .= '<td><strong>' . $k . '</strong></td>';
        } else {
            $output .= '<td>' . $v . '</td>';
        }
    }
    $output .= '</tr>';
}
$output .= '</table>';
echo $output;
}