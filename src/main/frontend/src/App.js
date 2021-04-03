import './main.css';
import './normalize.css';
import React from 'react';
import Chart from "react-apexcharts";
import ReactFlow from 'react-flow-renderer';

class ApexChart extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            series: [{
                name: 'Series 1',
                data: [80, 50, 30],
            }],
            options: {
                chart: {
                    height: 350,
                    type: 'radar',
                    dropShadow: {
                        enabled: true,
                        blur: 1,
                        left: 1,
                        top: 1
                    },
                    toolbar: {
                        show: false
                    }
                },
                title: {
                    text: 'Хисамов Искандер Равилевич',
                    align: 'center',
                    style: {
                        color: 'white'
                    }
                },
                stroke: {
                    width: 2
                },
                fill: {
                    opacity: 0.5
                },
                markers: {
                    size: 0
                },
                xaxis: {
                    categories: ['POL', 'CHL', 'UPR'],
                    labels: {
                        style: {
                            colors: ['red', 'green', 'blue']
                        }
                    }
                },
                yaxis: {
                    tickAmount: 5,
                    min: 0,
                    max: 100,
                    labels: {
                        style: {
                            colors: 'white'
                        }
                    }
                }
            },
        };
    }

    render() {
        return (
            <div className="app">
                <div className="row">
                    <div className="mixed-chart">
                        <Chart
                            options={this.state.options}
                            series={this.state.series}
                            type="radar"
                            width="500"
                        />
                    </div>
                </div>
            </div>
        );
    }
}

class RGraph extends React.Component{
    render() {
        const elements = [
            { id: "1", style: {background: "#000000", width: 500, height: 250, border: 'none'}, data: { label: <ApexChart /> }, position: { x: 500, y: 150 } },
            { id: "2", style: {background: "#000000", width: 500, height: 250, border: 'transparent'}, data: { label: <ApexChart /> }, position: { x: 400, y: 250 } },
            { id: "e1-2", source: "1", target: "2", animated: true }
        ];

        const graphStyles = { width: "100%", height: "1080px", backgroundColor: "black"};

        const BasicGraph = () => <ReactFlow elements={elements} style={graphStyles} />;

        return <BasicGraph />;
    }
}

class App extends React.Component {
    render() {
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
                        <RGraph />
                    </div>
                </main>
            </div>
        );
    }
}

export default App;