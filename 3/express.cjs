const express = require('express');
const mysql = require('mysql2');

const app = express();
app.use(express.json());

const conn = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '',
    database: 'ingatlanok'
});

conn.connect(err =>{
    if(err) console.log(err)
    else console.log('Connected to database')
})

app.get('/api/ingatlan', (req, res) => {
    conn.query('SELECT ingatlanok.id, kategoriak.nev AS kategoria, leiras, hirdetesDatuma, tehermentes, ar, kepUrl FROM ingatlanok INNER JOIN kategoriak on kategoria = kategoriak.id;', (err, results) => {
        if (err) {
            console.log(err)
            res.sendStatus(500)
        }
        else if(results){
            const refactoredResults = results.map(item => ({
                ...item, tehermentes: item.tehermentes === 1, hirdetesDatuma: item.hirdetesDatuma.toISOString().split('T')[0]
            }))
            res.status(200).json(refactoredResults)
        }
    })
})

app.post('/api/ingatlan', (req, res) => {
    const body = req.body;
    if (!body || Object.keys(body).length === 0) {
        return res.status(400).json('Hiányos adatok')
    }

    const columns = Object.keys(body).map(k => mysql.escapeId(k)).join(', ');
    const values = Object.values(body)
    const placeholders = Object.keys(body).map(() => '?').join(', ')
    const sql = `INSERT INTO ingatlanok (${columns}) VALUES (${placeholders});`
    

    conn.query(sql, values, (err, results) => {
        if (err) {
            console.log(err)
            res.sendStatus(500)
        }
        else {
            res.status(201).json({ id: results.insertId })
        }
    })
})

app.delete('/api/ingatlan/:id', (req, res) => {
    const id = req.params.id;

    conn.query(`DELETE FROM ingatlanok WHERE id = ?`, [id], (err, results) => {
        if (err) {
            console.log(err)
            res.sendStatus(500)
        }
        else if(results.affectedRows === 0){
            res.status(404).json("Az ingatlan nem létezik.")
        } 
        else {
            res.sendStatus(204)
        }
    })
})

const port = 3333;
app.listen(port, ()=>{
    console.log("Fut");
})