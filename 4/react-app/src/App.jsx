import { useState } from 'react'
import { Route, Routes } from 'react-router-dom'

import OpenPage from './components/OpenPage'
import NewAd from './components/NewAd'
import Offers from './components/Offers.jsx'

import './bootstrap.min.css'
import './openpage.css'

function App() {
  return (
    <>
      <Routes>
          <Route path="/" element={<OpenPage />} />
          <Route path="/newad" element={<NewAd />} />
          <Route path="/offers" element={<Offers />} />
      </Routes>
    </>
  )
}

export default App
