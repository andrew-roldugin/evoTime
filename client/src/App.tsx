import React, {useCallback, useEffect, useState} from 'react';
import logo from './logo.svg';
import './App.css';
import {FormattedDate, IntlProvider} from "react-intl";
import axios from "axios";


function App() {
    const [date, setDate] = useState('');

    useEffect(() => {
        axios('http://localhost:5555/api/test/connectToDB', {
            method: "get",
            headers: {
                'Content-Type': 'text/plain'
            },
            auth: {
                password: "1234",
                "username": "test_user"
            }
        })
            .then(res => setDate(res.data))
    }, [])

    return (
        <IntlProvider messages={{}} locale='ru' defaultLocale='ru'>
            <div className="App">
                <header className="App-header">
                    <img src={logo} className="App-logo" alt="logo"/>

                    {date.length > 0
                        ? <p>Соединение с сервером установлено <FormattedDate value={date}
                                                                              dateStyle={"long"} timeStyle={"long"}></FormattedDate></p>
                        : <p>Подключение...</p>}
                    <a
                        className="App-link"
                        href="https://ru.reactjs.org/"
                        target="_blank"
                        rel="noopener noreferrer"
                    >
                        Официальная документация React
                    </a>
                </header>
            </div>
        </IntlProvider>

    );
}

export default App;
