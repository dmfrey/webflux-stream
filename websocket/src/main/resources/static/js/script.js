
var ws = null;
var url = "ws://localhost:8765/events";

function setConnected( connected )
{
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    // document.getElementById('echo').disabled = !connected;
}

function connect() {

    ws = new WebSocket( url );
    ws.onopen = function() {
        setConnected( true );
        console.log( 'Info: Connection Established.' );
    };

    ws.onmessage = function( event) {
        console.dir( event );

        document.getElementById('event' ).innerHTML = event.data;

    };

    ws.onclose = function( event) {
        setConnected(false);
        console.log( 'Info: Closing Connection.' );
    };

}

function disconnect() {

    if( ws != null ) {
        ws.close();
        ws = null;
    }

    setConnected( false );

}

function log( message ) {

    var console = document.getElementById('logging');
    var p = document.createElement('p');
    p.appendChild(document.createTextNode(message));
    console.appendChild(p);

}