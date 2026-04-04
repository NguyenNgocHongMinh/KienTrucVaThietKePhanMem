const express = require("express");
const mysql = require("mysql2");

const app = express();

const db = mysql.createConnection({
  host: "mysql",
  user: "root",
  password: "root",
  database: "mydb"
});

db.connect(err => {
  if (err) {
    console.log("DB error:", err);
  } else {
    console.log("Connected MySQL");
  }
});

app.get("/", (req, res) => {
  res.send("Node + MySQL OK");
});

app.listen(3000, () => console.log("Server running"));