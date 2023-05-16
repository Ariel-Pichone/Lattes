'use client';

import { Inter } from 'next/font/google';
import styles from './page.module.css';
import { Button, Label, Modal, Select, TextInput } from 'flowbite-react';
import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import BarChart from './components/BarChart';

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
  const [data, setData] = useState(null);
  const [dataBar, setDataBar] = useState({
    labels: null,
    datasets: [],
  });
  const [pesquisador, setPesquisador] = useState(null);
  const [instituto, setInstituto] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const { register, handleSubmit } = useForm();

  function pesquisarProducao(data) {
    const termo = data.termo;
    const campo = data.campo;
    let url = '';
  }

  function agruparPorAno(data) {
    var totalPorAno = {};

    // Iterar sobre os dados e calcular o total de pesquisas por ano
    for (var i = 0; i < data.length; i++) {
      var ano = data[i].ano;
      if (totalPorAno[ano]) {
        totalPorAno[ano]++;
      } else {
        totalPorAno[ano] = 1;
      }
    }

    var listaObjetos = [];
    for (var ano in totalPorAno) {
      var objeto = {
        ano: ano,
        total: totalPorAno[ano],
      };
      listaObjetos.push(objeto);
    }

    setDataBar({
      labels: listaObjetos.map((data) => data.ano),
      datasets: [
        {
          label: 'Total prod.',
          data: listaObjetos.map((data) => data.total),
          backgroundColor: ['#1a56db'],
        },
      ],
    });

    // Exibir o total de pesquisas por ano
    // for (var ano in totalPorAno) {
    //   console.log('Ano: ' + ano + ', Total de pesquisas: ' + totalPorAno[ano]);
    // }
  }

  useEffect(() => {
    setLoading(true);
    fetch('http://localhost:8080/producao/')
      .then((res) => res.json())
      .then((data) => {
        setData(data);
        setLoading(false);
        agruparPorAno(data);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/instituto/')
      .then((res) => res.json())
      .then((instituto) => {
        setInstituto(instituto);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/pesquisador/')
      .then((res) => res.json())
      .then((pesquisador) => {
        setPesquisador(pesquisador);
        setLoading(false);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
    <div className="mx-2">
      <div className="flex justify-between items-center">
        <h1 className="text-4xl font-bold px-4 py-6">Painel Principal</h1>

        <form
          onSubmit={handleSubmit(pesquisarProducao)}
          className='flex justify-between items-center"'
        >
          <div className="block mr-4">
            <TextInput
              {...register('dataInicio')}
              className="w-23"
              id="termo"
              name="dataInicio"
              placeholder="Data Início"
            />
          </div>
          <div className="block mr-4">
            <TextInput
              {...register('dataFim')}
              className="w-23"
              id="termo"
              name="dataFim"
              placeholder="Data Fim"
            />
          </div>

          <div className="mr-4" id="select">
            <Select
              {...register('instituto')}
              id="camp"
              name="instituto"
              required={true}
            >
              <option value="">Instituto</option>
              {instituto &&
                instituto.map((instituto) => (
                  <option value={instituto.id}>{instituto.nome}</option>
                ))}
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select
              {...register('pesquisador')}
              id="campo"
              name="pesquisador"
              required={true}
            >
              <option value="">Pesquisador</option>
              {pesquisador &&
                pesquisador.map((pesquisador) => (
                  <option value={pesquisador.id}>{pesquisador.nome}</option>
                ))}
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select
              {...register('tipoProducao')}
              id="campo"
              name="tipoProducao"
              required={true}
            >
              <option>Tipo Prod.</option>
              <option>Artigo</option>
              <option>Livro</option>
            </Select>
          </div>
          <div className="flex justify-between items-center">
            <button
              type="submit"
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            >
              Pesquisar
            </button>
          </div>
        </form>
      </div>
      <div className="w-1/2">
        <BarChart chartData={dataBar} />
      </div>
    </div>
  );
}
