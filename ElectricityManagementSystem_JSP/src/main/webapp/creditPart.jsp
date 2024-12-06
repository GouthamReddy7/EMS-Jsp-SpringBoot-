<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Credits</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background: linear-gradient(to bottom right, #3a3d98, #141e30);
        color: white;
        overflow: hidden;
        height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    #credits-container {
        position: absolute;
        width: 100%;
        overflow: hidden;
    }

    .credits {
        text-align: center;
        animation: scrollCredits 10s linear infinite;
    }

    .credits h2 {
        font-size: 3rem;
        margin-bottom: 1rem;
        color: #ffcc00;
    }

    .credits p {
        font-size: 1.5rem;
        margin-bottom: 1.5rem;
    }

    .credits ul {
        list-style-type: none;
        padding: 0;
    }

    .credits li {
        font-size: 1.8rem;
        margin: 0.5rem 0;
        opacity: 0.9;
    }

    @keyframes scrollCredits {
        0% {
            transform: translateY(100%);
        }
        100% {
            transform: translateY(-100%);
        }
    }

    .go-back {
        position: fixed;
        bottom: 20px;
        right: 20px;
        background-color: #f4b400;
        color: #141e30;
        border: none;
        padding: 10px 20px;
        border-radius: 5px;
        font-size: 1rem;
        font-weight: bold;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s ease, color 0.3s ease;
    }

    .go-back:hover {
        background-color: #141e30;
        color: #f4b400;
    }
</style>
</head>
<body>
<div id="credits-container">
    <div class="credits">
        <h2>Thank You</h2>
        <p>We would like to express our gratitude to all team members:</p>
        <ul id="team-members">
            <!-- Team members will be populated dynamically -->
        </ul>
    </div>
</div>

<a href="javascript:history.back()" class="go-back">Go Back</a>

<script>
    // Team member names
    const teamMembers = [
        "Shivam Kumar",
        "Shruti Talukder",
        "Gokul MK",
        "Greeshma Kopparapu",
        "Sai Goutham",
        "Siddharth Sharma"
    ];

    // Add members to the list dynamically
    const teamList = document.getElementById('team-members');
    teamMembers.forEach(member => {
        const listItem = document.createElement('li');
        listItem.textContent = member;
        teamList.appendChild(listItem);
    });
</script>
</body>
</html>
