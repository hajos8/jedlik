import {Table} from 'react-bootstrap';
import { useState, useEffect } from 'react';

export default function Offers() {
    const [offers, setOffers] = useState([]);

    useEffect(() => {
        fetch('http://localhost:5000/api/ingatlan')
        .then(async res =>{
            const data = await res.json();
            console.log(data)
            setOffers(data);
        })
        .catch(err => { console.log(err); });
    }, [])

    return (
    <div className="page-wrapper">
        <Table striped bordered hover>
            <thead>
                <tr>
                    <th>Kategória</th>
                    <th>Leírás</th>
                    <th>Dátum</th>
                    <th>Tehermentes</th>
                    <th>Kép</th>
                </tr>
            </thead>
            <tbody>
                {offers.map(offer => (
                    <tr key={offer.id}>
                        <td>{offer.kategoriaNev}</td>
                        <td>{offer.leiras}</td>
                        <td>{new Date(offer.hirdetesDatuma).toLocaleDateString()}</td>
                        <td>{offer.tehermentes ? 'Igen' : 'Nem'}</td>
                        <td>
                            {offer.kepUrl && <img src={offer.kepUrl} alt="Ingatlan" style={{width: '100px'}} />}
                        </td>
                    </tr>
                ))}
            </tbody>
            
        </Table>
    </div>
    )
}