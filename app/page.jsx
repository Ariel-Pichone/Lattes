'use client';

import { Inter } from 'next/font/google';
import styles from './page.module.css';
import { Button, Label, Modal, Select, TextInput, Card } from 'flowbite-react';
import { useState, useEffect } from 'react';
import { useForm } from 'react-hook-form';
import BarChart from './components/BarChart';
import DoughnutChart from './components/DoughnutChart';
import { Doughnut } from 'react-chartjs-2';

const inter = Inter({ subsets: ['latin'] });

export default function Home() {
  const [data, setData] = useState(null);
  const [dataBar, setDataBar] = useState({
    labels: null,
    datasets: [],
  });
  const [dataDoughnut, setDataDoughnut] = useState({
    labels: null,
    datasets: [],
  });

  const [pesquisador, setPesquisador] = useState(null);
  const [instituto, setInstituto] = useState(null);
  const [totalInstitutos, setTotalInstitutos] = useState(0);
  const [totalPesquisadores, setTotalPesquisadores] = useState(0);
  const [countTotalProducoesPorTipo, setCountTotalProducoesPorTipo] =
    useState(null);
  const [isLoading, setLoading] = useState(true);
  const { register, handleSubmit } = useForm();

  function pesquisarProducao(data) {
    const termo = data.termo;
    const campo = data.campo;
    let url = '';
  }

  function agruparPorAno(data) {
    setDataBar({
      labels: data.map((data) => data.anoProducao),
      datasets: [
        {
          label: 'Total prod.',
          data: data.map((data) => data.totalProducao),
          backgroundColor: ['#1a56db'],
        },
      ],
    });
  }

  function insertDataDoughnut(countTotalProducoesPorTipo) {
    setDataDoughnut({
      labels: countTotalProducoesPorTipo.map((data) => data.tipoProducao),
      datasets: [
        {
          label: 'total Prod.',
          data: countTotalProducoesPorTipo.map((data) => data.totalProducao),
          backgroundColor: ['#1a56db', 'rgba(54, 162, 235, 0.2)'],
        },
      ],
    });
  }

  function pesquisarProducao({
    dataInicio,
    dataFim,
    instituto,
    pesquisador,
    tipoProducao,
  }) {
    fetch(
      `http://localhost:8080/producao/filtro?dataInicio=${dataInicio}&dataFim=${dataFim}&instituto=${instituto}&pesquisador=${pesquisador}&tipoProducao=${tipoProducao}`
    )
      .then((res) => res.json())
      .then((data) => {
        setData(data);
        console.log(data);
      })
      .catch((err) => console.log(err));
  }

  useEffect(() => {
    setLoading(true);
    fetch(`http://localhost:8080/producao/countTotalProducoesPorAno`)
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

    fetch('http://localhost:8080/instituto/count')
      .then((res) => res.json())
      .then((totalInstitutos) => {
        setTotalInstitutos(totalInstitutos);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/pesquisador/count')
      .then((res) => res.json())
      .then((totalPesquisador) => {
        setTotalPesquisadores(totalPesquisador);
        setLoading(false);
      })
      .catch((err) => console.log(err));

    fetch('http://localhost:8080/producao/countTotalProducoesPorTipo')
      .then((res) => res.json())
      .then((countTotalProducoesPorTipo) => {
        setCountTotalProducoesPorTipo(countTotalProducoesPorTipo);
        insertDataDoughnut(countTotalProducoesPorTipo);
        // console.log(dataDoughnut);
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
            <Select {...register('instituto')} id="camp" name="instituto">
              <option value="">Instituto</option>
              {instituto &&
                instituto.map((instituto) => (
                  <option value={instituto.id}>{instituto.nome}</option>
                ))}
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select {...register('pesquisador')} id="campo" name="pesquisador">
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
            >
              <option value="">Tipo Prod.</option>
              <option>Artigo</option>
              <option>Livro</option>
            </Select>

            {/* <Controller
        render={({ field: { onChange, value } }) => (
          <Select
            options={[
              { value: "", label: "Tipo Prod." },
              { value: "Artigo", label: "Artigo" },
              { value: "Livro", label: "Livro" }
            ]}
            onChange={(e) => {
              // onChange's arg will send value into hook form
              onChange(e.value);
            }}
            value={{
              // make sure we remain the corect format for the controlled component
              value: value,
              label: value
            }}
          />
        )}
        name="tipoProducao"
        control={control}
        defaultValue={null}
      /> */}
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
      <div className=" pb-16">
        <BarChart chartData={dataBar} className="w-full" />
      </div>

      <div className="grid gap-x-8 gap-y-4 grid-cols-4">
        <Card
          href="/producao"
          className="row-span-1 bg-zinc-800 text-zinc-400 hover:bg-zinc-900"
        >
          <h3 className="text-3xl font-normal  dark:text-gray-400">
            Total Produção
          </h3>
          <DoughnutChart dataDoughnut={dataDoughnut} />
        </Card>

        <Card
          href="/instituto"
          className="row-span-1 bg-zinc-800 text-zinc-400 hover:bg-zinc-900"
        >
          <h3 className="text-3xl font-normal  dark:text-gray-400">
            Institutos
          </h3>
          <p className="text-5xl font-bold tracking-tight  dark:text-white">
            {totalInstitutos}
          </p>
        </Card>

        <Card
          href="/pesquisador"
          className="row-span-1 bg-zinc-800 text-zinc-400 hover:bg-zinc-900"
        >
          <h3 className="text-3xl font-normal  dark:text-gray-400">
            Pesquisadores
          </h3>
          <p className="text-5xl font-bold tracking-tight  dark:text-white">
            {totalPesquisadores}
          </p>
        </Card>

        <Card
          href="/grafo"
          className="row-span-1 bg-zinc-800 text-zinc-400 hover:bg-zinc-900"
        >
          <h3 className="text-3xl font-normal  dark:text-gray-400">Grafos</h3>
          <p className="text-5xl font-bold tracking-tight dark:text-white">
            GRAFOS
          </p>
        </Card>
      </div>
    </div>
  );
}
