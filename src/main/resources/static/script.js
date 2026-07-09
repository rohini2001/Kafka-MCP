// =========================
// AI Kafka Assistant
// script.js
// =========================

const chat = document.getElementById("chat");
const input = document.getElementById("prompt");
const sendButton = document.getElementById("sendButton");
const clearButton = document.getElementById("clearChat");

// ----------------------------
// Send button click
// ----------------------------
if (sendButton) {
    sendButton.addEventListener("click", sendMessage);
}

// ----------------------------
// Press Enter key
// ----------------------------
if (input) {
    input.addEventListener("keypress", function(e) {
        if (e.key === "Enter") {
            sendMessage();
        }
    });
}

// ----------------------------
// Suggested prompts click
// ----------------------------
document.querySelectorAll(".suggestion").forEach(button => {
    button.addEventListener("click", function() {
        if (input) {
            // Strips out emojis if they exist at the beginning of suggestions
            input.value = this.innerText.replace(/^[\u0000-\u1F6FF\s]+/, '').trim();
            sendMessage();
        }
    });
});

// ----------------------------
// Clear Chat Window
// ----------------------------
if (clearButton) {
    clearButton.addEventListener("click", function() {
        chat.innerHTML = `
            <div class="message bot">
                <div class="avatar">🤖</div>
                <div class="bubble">Chat history cleared. How can I help you manage Kafka today?</div>
            </div>
        `;
        scrollBottom();
    });
}

// ----------------------------
// Add User Message
// ----------------------------
function addUserMessage(message) {
    chat.innerHTML += `
    <div class="message user">
        <div class="bubble">
            ${message}
        </div>
    </div>
    `;
    scrollBottom();
}

// ----------------------------
// Add AI Message
// ----------------------------
function addBotMessage(message) {
    // Check if markdown parser exists, fallback safely if not loaded yet
    const parsedContent = typeof marked !== 'undefined' ? marked.parse(message) : message;

    chat.innerHTML += `
    <div class="message bot">
        <div class="avatar">🤖</div>
        <div class="bubble">
            ${parsedContent}
        </div>
    </div>
    `;
    scrollBottom();
}

// ----------------------------
// Typing Indicator
// ----------------------------
function showTyping() {
    chat.innerHTML += `
    <div class="message bot" id="typing">
        <div class="avatar">🤖</div>
        <div class="bubble typing-dots">
            AI is thinking<span>.</span><span>.</span><span>.</span>
        </div>
    </div>
    `;
    scrollBottom();
}

function hideTyping() {
    const typing = document.getElementById("typing");
    if (typing) {
        typing.remove();
    }
}

// ----------------------------
// Send Message Action
// ----------------------------
async function sendMessage() {
    const prompt = input.value.trim();
    if (prompt === "") return;

    addUserMessage(prompt);
    input.value = "";
    showTyping();

    try {
        const response = await fetch("/api/ai/generate?prompt=" + encodeURIComponent(prompt));
        const answer = await response.text();
        hideTyping();
        addBotMessage(answer);
    } catch (e) {
        hideTyping();
        addBotMessage("❌ Unable to connect to the AI service. Please verify your cluster backend.");
    }
}

// ----------------------------
// Smooth Auto Scroll
// ----------------------------
function scrollBottom() {
    chat.scrollTo({
        top: chat.scrollHeight,
        behavior: 'smooth'
    });
}

// ----------------------------
// Load Topic Count
// ----------------------------
async function loadTopicCount() {
    try {
        const response = await fetch("/kafka/topics");
        const topics = await response.json();

        const countEl = document.getElementById("topicCount");
        const statusEl = document.getElementById("clusterStatus");

        if (countEl) countEl.innerText = topics.length;
        if (statusEl) statusEl.innerText = "Healthy";
    } catch (e) {
        const statusEl = document.getElementById("clusterStatus");
        if (statusEl) statusEl.innerText = "Offline";
    }
}

// Initialize on Load & Poll
loadTopicCount();
setInterval(loadTopicCount, 15000);