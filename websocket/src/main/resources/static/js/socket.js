var stompClient = null;

function connect() {
    var socket = new SockJS( '/endpoint' );
    stompClient = Stomp.over( socket );
    stompClient.connect( {}, function( frame ) {
        console.log( 'Connected: ' + frame );
        document.getElementById('event' ).innerHTML = "Connected!";
        stompClient.subscribe('/topic/events', function( entry ) {
            console.dir( entry );
            showEntry( JSON.parse( entry.body ) );
        });
    });
}

function showEntry( entry ) {

    document.getElementById('event' ).innerHTML = entry;

    // createNotification( action, message );

}

$(function () {
    connect();
});

// // function for creating the notification
// function createNotification(action, message) {
//
//     // Let's check if the browser supports notifications
//     if (!"Notification" in window) {
//         console.log("This browser does not support notifications.");
//     }
//
//     // Let's check if the user is okay to get some notification
//     else if (Notification.permission === "granted") {
//         // If it's okay let's create a notification
//
//         var notification = new Notification('Work Order ' + action, {body: message});
//
//     }
//
//     // Otherwise, we need to ask the user for permission
//     // Note, Chrome does not implement the permission static property
//     // So we have to check for NOT 'denied' instead of 'default'
//     else if (Notification.permission !== 'denied') {
//         Notification.requestPermission(function (permission) {
//
//             // Whatever the user answers, we make sure Chrome stores the information
//             if (!('permission' in Notification)) {
//                 Notification.permission = permission;
//             }
//
//             // If the user is okay, let's create a notification
//             if (permission === "granted") {
//                 var notification = new Notification('Work Order ' + action, {body: message});
//
//             }
//         });
//     }
// }