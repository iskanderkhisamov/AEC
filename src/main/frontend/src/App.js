import './main.css';
import './normalize.css';
import React from 'react';
import Chart from "react-apexcharts";
import ReactFlow from 'react-flow-renderer';
import axios from "axios";

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
                    text: this.props.name,
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
    constructor(props) {
        super(props);
    }
    render() {
        const elements = [
            { id: "1", style: {background: "rgba(0,0,0,0)", width: 500, height: 250, border: 'none'}, data: {
                label: <ApexChart name={this.props.name}/> }, position: { x: 500, y: 150 } },
            { id: "2", style: {background: "rgba(0,0,0,0)", width: 500, height: 250, border: 'transparent'}, data: {
                label: <ApexChart name={this.props.name}/> }, position: { x: 400, y: 250 } },
            { id: "e1-2", source: "1", target: "2", animated: true }
        ];

        const graphStyles = { width: "100%", height: "1080px", backgroundColor: "black"};

        const BasicGraph = () => <ReactFlow elements={elements} style={graphStyles} />;

        return <BasicGraph />;
    }
}

class App extends React.Component {
    state = {
        name: "b"
    }

    getUsers = () => {
        axios
            .get("http://localhost:8080/statistics/tupak")
            .then(data => this.setState({ name: data.data.name }))
            .catch(err => {
                console.log(err);
                return null;
            });
    };

    componentDidMount() {
        this.getUsers();
    }

    render() {
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
                        {console.log(this.state.name)}
                        {this.state.name.length === 1 ? (<div>Loading...</div>) : (<RGraph name={this.state.name}/>)}
                        {console.log(this.state.name)}
                    </div>
                </main>
            </div>
        );
    }
}

export default App;