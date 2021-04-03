import './main.css';
import './normalize.css';
import Service from "./Service";
function App() {
    return (
        <div className="wrapper">
            <header className="header">
                <div className="container">
                    <div className="header__title">
                        <a href="http://localhost:8080/">Автоматизированный учебный курс</a>
                    </div>
                    <nav className="header__nav">
                        <a href="http://localhost:8080/profile">Личный кабинет</a>
                        <a href="http://localhost:8080/tests">Тесты</a>
                        <a href="" className="selected">Статистика</a>
                    </nav>
                </div>
            </header>
            <main className="main">
                <div className="container">
                    {Service.retrieveScholar()}
                    {console.log("aha")}
                </div>
            </main>
        </div>
    );
}

export default App;
