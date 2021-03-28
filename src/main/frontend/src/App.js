import './main.css';
import './normalize.css';
import {Radar} from 'react-chartjs-2';

const state = {
    labels: ['January', 'February', 'March',
        'April', 'May'],
    datasets: [
        {
            label: 'Rainfall',
            backgroundColor: 'rgba(75,192,192,1)',
            borderColor: 'rgba(0,0,0,1)',
            borderWidth: 2,
            data: [65, 59, 80, 81, 56]
        }
    ]
}

function App() {
    return (
        <div className="wrapper">
            <header className="header">
                <div className="container">
                    <div className="header__title">
                        <a href="/">Автоматизированный учебный курс</a>
                    </div>
                    <nav className="header__nav">
                        <a href="/profile">Личный кабинет</a>
                        <a href="/tests">Тесты</a>
                        <a href="" className="selected">Статистика</a>
                    </nav>
                </div>
            </header>
            <main className="main">
                <div className="container">
                    <Radar
                        data={state}
                        options={{
                            title:{
                                display:true,
                                text:'Average Rainfall per month',
                                fontSize:20
                            },
                            legend:{
                                display:true,
                                position:'right'
                            }
                        }}
                    />
                </div>
            </main>
        </div>
    );
}

export default App;
