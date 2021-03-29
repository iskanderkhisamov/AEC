import './main.css';
import './normalize.css';
import {Radar} from 'react-chartjs-2';
import React from 'react';
import { ForceDirectedGraphChart } from 'chartjs-chart-graph';
import Canvas from "./Canvas";

function transp(r, g, b, a) {
    r = r.toString(16);
    g = g.toString(16);
    b = b.toString(16);
    a = Math.round((a - 0.5) * 255).toString(16);

    if (r.length == 1)
        r = "0" + r;
    if (g.length == 1)
        g = "0" + g;
    if (b.length == 1)
        b = "0" + b;
    if (a.length == 1)
        a = "0" + a;

    return "#" + r + g + b + a;
}

const commonColor = 'rgb(115, 0, 230, 1)'
const state = {
    labels: ['Running', 'Swimming', 'Eating', 'Cycling', 'Travelling'],
    datasets: [{
        backgroundColor: transp(115,0,230,1),
        borderColor: commonColor,
        data: [20, 10, 20, 13, 5],
        label: 'lie'
    }]
}

function Liner() {
    new ForceDirectedGraphChart(document.getElementById("id").getContext("2d"), {
        data: {
            labels: [
                { "name": "1" },
                { "name": "11", "parent": 0 },
                { "name": "111", "parent": 1 },
                { "name": "1111", "parent": 2 },
                { "name": "1112", "parent": 2 },
                { "name": "112", "parent": 1 },
                { "name": "1121", "parent": 5 },
                { "name": "1122", "parent": 5 },
                { "name": "113", "parent": 1 },
                { "name": "1131", "parent": 8 },
                { "name": "1132", "parent": 8 },
                { "name": "12", "parent": 0 },
                { "name": "121", "parent": 11 },
                { "name": "1211", "parent": 12 },
                { "name": "1212", "parent": 12 },
                { "name": "122", "parent": 11 },
                { "name": "1221", "parent": 15 },
                { "name": "1222", "parent": 15 },
                { "name": "123", "parent": 11 },
                { "name": "1231", "parent": 18 },
                { "name": "1232", "parent": 18 },
                { "name": "13", "parent": 0 },
                { "name": "131", "parent": 21 }
            ],
            datasets: [{
                pointBackgroundColor: 'steelblue',
                pointRadius: 5,
                data: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23],
            }]
        },
        options: {
            tree: {
                mode: 'tree'
            },
            legend: {
                display: false
            },
            layout: {
                padding: {
                    left: 5,
                    top: 5,
                    bottom: 5,
                    right: 5
                }
            }
        }
    })
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
                            maintainAspectRatio: true,
                            spanGaps: false,
                            elements: {
                                line: {
                                    tension: 0.000001
                                }
                            },
                            scale: {
                                gridLines: {
                                    color: 'rgb(41, 41, 61)',
                                    lineWidth: 2
                                },
                                tick: {
                                    fontsize: 28,
                                    min: 0,
                                    max: 50
                                }
                            },
                            plugins: {
                                'samples-filler-analyser': {
                                    target: 'chart-analyser'
                                }
                            }
                        }}
                    />
                    <div className="container">
                        <Canvas
                            draw={ctx => {
                                ctx.beginPath();
                                ctx.arc(95, 50, 40, 0, 2 * Math.PI);
                                ctx.closePath();
                                ctx.stroke();
                            }
                            }
                        />
                    </div>
                </div>
            </main>
        </div>
    );
}

export default App;
