import { useState, useEffect } from "react";
import '../openpage.css';
import { useNavigate } from "react-router-dom";

async function fetchKategoriak(setKategoriak) {
    const json = await fetch('http://localhost:5000/api/kategoriak')
    const data = await json.json();
    console.log("fetchKategoriak data: ", data);
    setKategoriak(data);
}

export default function NewAd() {
    const [tehermentes, setTehermentes] = useState(true);
    const [hiba, setHiba] = useState(null);
    const [kategoriak, setKategoriak] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
        fetchKategoriak(setKategoriak);
    }, []);

    const handleTehermentesChange = (event) => {
        setTehermentes(!tehermentes);
    }

    const handleSubmit = async e =>{
        e.preventDefault();
        console.log(e)

        const form = e.target;
        const formData = {
            kategoriaId: +form.kategoriaId.value,
            leiras: form.leiras.value,
            hirdetesDatuma: form.hirdetesDatuma.value,
            tehermentes: form.tehermentes.checked,
            kepUrl: form.kepUrl.value
        }

        console.log("formData: ", formData);
        const requestBodyJson = JSON.stringify(formData);
        const responseJson = await fetch('http://localhost:5000/api/ujingatlan', {
            method: 'POST',
            headers: {"Content-Type": "application/json"},
            body: requestBodyJson
        })
        console.log("responseJson: ", responseJson);
        if(responseJson.ok){
            form.reset();
            navigate('/offers');
        } else {
            setHiba('Hiba történt a hirdetés feladásakor');
        }
    }

    return (
        <div className="container">
            <h2 className="mb-4 text-center">Új hirdetés elküldése</h2>
            <div className="row">
                <form className="offset-lg-3 offset-md-2 col-lg-6 col-md-8 col-12" onSubmit={handleSubmit}>
                    <div className="mb-3">
                        <label htmlFor="category" className="form-label">Ingatlan kategóriája</label>
                        <select className="form-select" name="kategoriaId">
                            <option value="0">Kérem válasszon</option>
                            {kategoriak.map(kategoria => <option key={kategoria.id} value={kategoria.id}>
                                {kategoria.megnevezes}
                            </option>)
                            }
                        </select>
                    </div>

                    <div className="mb-3">
                        <label htmlFor="date" className="form-label">Hirdetés dátuma</label>
                        <input type="date" className="form-control" name="hirdetesDatuma" readOnly value={new Date().toISOString().split('T')[0]}/>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="description" className="form-label">Ingatlan leírása</label>
                        <textarea className="form-control" name="leiras" rows="3" required></textarea>
                    </div>
                    <div className="form-check mb-3">
                        <input className="form-check-input" type="checkbox" name="tehermentes" checked value={tehermentes} onChange={handleTehermentesChange}/>
                        <label className="form-check-label" htmlFor="creditFree">Tehermentes ingatlan</label>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="pictureUrl" className="form-label">Fénykép az ingatlanról</label>
                        <input type="url" className="form-control" name="kepUrl" required/>
                    </div>
                    <div className="mb-3 text-center">
                        <button className="btn btn-primary px-5" type="submit">Küldés</button>
                    </div>

                    {hiba && <div className="alert alert-danger alert-dismissible" role="alert">
                        <strong>Hiba szövege</strong>
                        <button type="button" className="btn-close"></button>
                    </div>}

                </form>
            </div>
        </div>
    )
}