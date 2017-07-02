<HTML>
<HEAD>
<TITLE>Crunchify - jQuery Knob Example</TITLE>
 
<style type="text/css">
body {
    background-image:
        url('https://cdn.crunchify.com/wp-content/uploads/2013/03/Crunchify.bg_.300.png');
}
</style>
<script type="text/javascript"
    src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="https://cdn.crunchify.com/wp-content/uploads/code/knob.js"></script>
<script>
 
function knobfunction(value1){
    $('.dial')
    .val(value1)
    .trigger('change');
}
 
    $(document).ready(
            function() {
                setInterval(function() {
                    var randomnumber = Math.round(Math.random() * 100);
                    knobfunction(randomnumber);
                }, 3000);
            });
</script>
 
</HEAD>
<BODY>
    <br>
 
    <div id="show" align="center"></div>
    <div align="center">
        <input type="text" class="dial"> <br><br>
        <div
            style="font-family: verdana; padding: 10px; border-radius: 10px; border: 3px solid #EE872A; width: 50%; font-size: 12px;">
 
            jQuery Knob Example by <a
                href='https://crunchify.com'>Crunchify</a>. Click <a
                href='https://crunchify.com/category/java-tutorials/'>here</a>
            for all Java, Spring MVC, Web Development examples.<br>
        </div>
    </div>
    <script>
    $(".dial").knob({
                 'change' : function (v) { console.log(v); }
    });
</script>
 
</BODY>
</HTML>






