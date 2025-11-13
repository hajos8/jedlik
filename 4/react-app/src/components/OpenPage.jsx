import { Link } from "react-router-dom";
import { useState, useEffect } from "react";

import '../openpage.css';

export default function OpenPage() {
    return(
        <div className="container start">
            <div className="start w-100">
                <h1 className="text-center pt-2 pt-lg-4">Á.L.B. Ingatlanügynöség</h1>
                <div className="row">
                    <div className="col-12 col-sm-6 text-center">
                        <Link to="/offers">Nézze meg kínálatunkat!</Link>
                    </div>
                    <div className="col-12 col-sm-6 text-center">
                        <Link to="/newad">Hirdessen nálunk!</Link>
                    </div>
                </div>
            </div>
        </div>
    )
}