var messagesOutput, userNameInput, messageInput, eventSource;

window.addEventListener("load", function () {
        messagesOutput = document.getElementById("message");

        // Connect to the server
        eventSource = new EventSource("/sse");

        // Receive messages
        eventSource.onmessage = function (event) {
            messagesOutput.value += event.data + '\r';
        };
        eventSource.onerror = function (e) {
            console.log("EventSource failed: " + e);
        };
    },
    false);