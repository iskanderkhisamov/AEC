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
            { id: "1",
                style: {background: "rgba(0,0,0,0)", width: 480, height: 250, border: 'none'},
                data: {
                    label: <ApexChart name={'Хисамов Искандер Равилевич'}/>
                },
                position: { x: 250, y: 100 }
            },
            { id: "2",
                style: {background: "rgba(0,0,0,0)", width: 480, height: 250, border: 'none'},
                data: {
                    label: <ApexChart name={this.props.name}/>
                },
                position: { x: 100, y: 550 }
            },
            { id: "3",
                style: {background: "rgba(0,0,0,0)", width: 480, height: 250, border: 'none'},
                data: {
                    label: <ApexChart name={'Философия'}/>
                },
                position: { x: 400, y: 550 }
            },
            { id: "e1-2", source: "1", target: "2", animated: true },
            { id: "e1-3", source: "1", target: "3", animated: true }
        ];

        const graphStyles = { width: "100%", height: "1080px", backgroundColor: "#474B4F"};

        const BasicGraph = () => <ReactFlow elements={elements} style={graphStyles} nodesConnectable={false}/>;

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
                <main className="main gray">
                        {this.state.name.length === 1 ? (<div>Loading...</div>) : (<RGraph name={this.state.name}/>)}
                </main>
            </div>
        );
    }
}

export default App;