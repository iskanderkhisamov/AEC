import './main.css';
import './normalize.css';

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

          </div>
        </main>
      </div>
  );
}

export default App;
